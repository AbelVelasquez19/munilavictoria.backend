package pe.gob.mlvictoria.pagolinea.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.gob.mlvictoria.pagolinea.dto.pago.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import pe.gob.mlvictoria.utility.VisaConfig;

@Slf4j
@Service
public class NiubizService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final VisaConfig visaConfig;

    public NiubizService(RestTemplate restTemplate, ObjectMapper objectMapper, VisaConfig visaConfig) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.visaConfig = visaConfig;
    }

    public String getAccessToken() {
        try {
            String credentials = visaConfig.getUser() + ":" + visaConfig.getPwd();
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
            headers.set(HttpHeaders.ACCEPT, "*/*");

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<String> resp = restTemplate.exchange(
                    visaConfig.getUrl().getSecurity(),
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (!resp.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Niubiz respondió: " + resp.getStatusCode());
            }

            String token = (resp.getBody() == null) ? "" : resp.getBody().trim();
            if (token.isEmpty()) {
                throw new RuntimeException("El token recibido está vacío.");
            }
            return token;

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            throw new RuntimeException("HTTP " + e.getStatusCode() + ": " + e.getResponseBodyAsString(), e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener token de Niubiz: " + ex.getMessage(), ex);
        }
    }

    public SessionResponseDTO getSessionToken(SessionRequestDTO dto) {
       final String url = visaConfig.getUrl().getSession();
        dto.setAmount(new BigDecimal(formatAmount(dto.getAmount())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, getAccessToken().trim());

        HttpEntity<SessionRequestDTO> request = new HttpEntity<>(dto, headers);

        try {
            ResponseEntity<SessionResponseDTO> response = restTemplate.postForEntity(url, request, SessionResponseDTO.class);
            if (response.getStatusCode().is2xxSuccessful()
                    && response.getBody() != null
                    && response.getBody().getSessionKey() != null) {
                return response.getBody();
            }
            throw new RuntimeException("Niubiz no devolvió sessionKey. Código: " + response.getStatusCode());
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Error " + e.getStatusCode() + " → " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener token de sesión: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> authorizeTransaction(
            HttpServletRequest request,
            String tokenId,
            String purchaseNumber,
            BigDecimal amount,
            String currency
    ) {
        final String url = visaConfig.getUrl().getAuthorization();
        //final BigDecimal amt = amount.setScale(2, RoundingMode.HALF_UP);
        final BigDecimal amt = new BigDecimal(formatAmount(amount));

        final String urlAddress = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .build()
                .toUriString();

        Map<String, Object> order = new LinkedHashMap<>();
        order.put("amount", amt);
        order.put("currency", currency);
        order.put("purchaseNumber", purchaseNumber);
        order.put("tokenId", tokenId);

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("urlAddress", urlAddress);
        var d = visaConfig.getDefaults(); // si no tienes defaults en config, puedes dejar los LIMA “en duro” aquí
        dataMap.put("partnerIdCode", d != null ? d.getPartnerIdCode() : "");
        dataMap.put("serviceLocationCityName", d != null ? d.getServiceLocationCityName() : "LIMA");
        dataMap.put("serviceLocationCountrySubdivisionCode", d != null ? d.getServiceLocationCountrySubdivisionCode() : "LIMA");
        dataMap.put("serviceLocationCountryCode", d != null ? d.getServiceLocationCountryCode() : "PER");
        dataMap.put("serviceLocationPostalCode", d != null ? d.getServiceLocationPostalCode() : "15018");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("captureType", "manual");
        body.put("channel", "web");
        body.put("countable", true);
        body.put("order", order);
        body.put("dataMap", dataMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String securityToken = getAccessToken();
        if (securityToken != null) securityToken = securityToken.replace("\"", "").trim();
        headers.set(HttpHeaders.AUTHORIZATION, securityToken);
        headers.set("merchantId", visaConfig.getMerchantId());

        try {
            ResponseEntity<Map<String, Object>> resp = restTemplate.exchange(
                    url, HttpMethod.POST, new HttpEntity<>(body, headers),
                    new ParameterizedTypeReference<>() {}
            );
            if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
                throw new RuntimeException("Autorización Niubiz: " + resp.getStatusCode());
            }
            return resp.getBody();

        } catch (HttpClientErrorException e) {
            String raw = e.getResponseBodyAsString();
            try { return objectMapper.readValue(raw, new TypeReference<>() {}); }
            catch (Exception ignore) { throw new RuntimeException("HTTP " + e.getStatusCode() + ": " + raw, e); }
        }

    }

    private String formatAmount(BigDecimal amount) {
        return amount == null ? "0.00" : amount.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
