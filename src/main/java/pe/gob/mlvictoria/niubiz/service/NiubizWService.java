package pe.gob.mlvictoria.niubiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.gob.mlvictoria.utility.VisaConfig;

import java.util.HashMap;
import java.util.Map;

@Service
public class NiubizWService {
    private final VisaConfig visaConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public NiubizWService(RestTemplate restTemplate, VisaConfig visaConfig, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.visaConfig = visaConfig;
        this.objectMapper = objectMapper;
    }

    // Servicio para generar token de acceso
    public String generateAccessToken(){
        String url = visaConfig.getUrl().getSecurity();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(visaConfig.getUser(), visaConfig.getPwd());

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,requestEntity, String.class);
            //200 y 201
            if(response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED){
                String accessToken = response.getBody();
                return accessToken;
            }else{
                throw new RuntimeException("Respuesta inesperada al obtener el token de acceso: " + response.getStatusCode());
            }
        }catch (Exception e) {
            throw new RuntimeException("Error al obtener el token de acceso: " + e.getMessage(), e);
        }
    }

    // Servicio de generar token de sesion
    public String generateSessionToken(String accessToken, double amount,String purchaseNumber, String clientIp, String email, String dni) {
        String sessionUrl = visaConfig.getUrl().getSession();
        RestTemplate restTemplate = new RestTemplate();

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("channel", "web");
        requestBody.put("amount", amount);
        requestBody.put("purchaseNumber", purchaseNumber);

        Map<String, Object> antifraud = new HashMap<>();
        antifraud.put("clientIp",clientIp);
        Map<String, Object> merchantDefineData = new HashMap<>();
        merchantDefineData.put("MDD4", email); //email
        merchantDefineData.put("MDD32", dni); //dni
        merchantDefineData.put("MDD75", "Invitado");
        merchantDefineData.put("MDD77", 458);
        antifraud.put("merchantDefineData", merchantDefineData);
        requestBody.put("antifraud", antifraud);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("cardholderCity", "Lima");
        dataMap.put("cardholderCountry", "PE");
        dataMap.put("cardholderAddress", "Unión Panamericana Parque 411, 15034 La Victoria, Perú");
        dataMap.put("cardholderPostalCode", "15018");
        dataMap.put("cardholderState", "LIM");
        dataMap.put("cardholderPhoneNumber", "015102070");
        requestBody.put("dataMap", dataMap);

        // Solicitud HTTP
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(sessionUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Parsear body para obtener el toekn de sesion
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> responseBody = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
                String sessionKey = (String) responseBody.get("sessionKey");
                Long expirationTime = (Long) responseBody.get("expirationTime");
                System.out.println("¡Token de sesión recibido!, Vigencia: " + expirationTime);
                return sessionKey;
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al procesar la respuesta del token de sesión", e);
            }
        }else {
            throw new RuntimeException("Error al generar el token de sesión: " + response.getBody());
        }
    }

    //Servicio para generar el token de autorizacion
    public Map<String, Object> generateAuthorizationToken(String accessToken, Map<String, Object> order) {
        try {
            // Merchant ID(utilixamos el default del ejemplo de niubiz)
            String merchantId = visaConfig.getMerchantId();

            // Configurar headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            // request
            Map<String, Object> requestBody = Map.of(
                    "channel", "web",
                    "captureType", "manual",
                    "countable", true,
                    "order", order
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            // Enviar solicitud HTTP
            ResponseEntity<Map> response = restTemplate.exchange(
                    visaConfig.getUrl().getAuthorization(),
                    HttpMethod.POST,
                    request,
                    Map.class,
                    merchantId
            );

            // Exitoso?
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Autorización exitosa: " + response.getBody());
                return response.getBody();
            } else {
                throw new RuntimeException("Error en la autorización: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            // AQUÍ LLEGA TODA TRAMA 400, 401, 403, 500
            System.out.println("Respuesta de error Niubiz: " + e.getResponseBodyAsString());

            try {
                // Convertir el JSON del error en Map
                return objectMapper.readValue(
                        e.getResponseBodyAsString(),
                        new TypeReference<Map<String,Object>>() {}
                );
            } catch (Exception ex) {
                throw new RuntimeException("Error procesando trama de error Niubiz", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el Authorization Token: " + e.getMessage(), e);
        }
    }
}
