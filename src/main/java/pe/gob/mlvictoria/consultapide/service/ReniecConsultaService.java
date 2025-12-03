package pe.gob.mlvictoria.consultapide.service;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pe.gob.mlvictoria.consultapide.dto.*;
import pe.gob.mlvictoria.consultapide.model.PideReniec;
import pe.gob.mlvictoria.utility.ReniecConfig;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ReniecConsultaService {

    //44069651

    @Autowired
    private final ReniecConfig reniecConfig;

    @Autowired
    private final ReniecServiceImpl reniecService;

    @Autowired
    private final RestTemplate restTemplate;

    @Retry(name = "reniecService")
    public ReniecResponseDTO consultarReniec(ReniecRequestDTO requestDTO,HttpServletRequest req) {
        //RestTemplate restTemplate = new RestTemplate();

        ReniecResponseDTO body;
        try {
            DatosPersonaLocalDTO local = reniecService.buscarPersonaLocal(requestDTO.getNumDocumento());
            if(local != null){
                log.info("Consulta en BD local para DNI");

                // Construir respuesta desde BD local
                ReturnData returnData = new ReturnData();
                returnData.setCoResultado("0000");
                returnData.setDeResultado("Consulta realizada desde BD local");
                DatosPersona datosPersona = new DatosPersona();
                datosPersona.setNumDocumento(local.getNumDocumento());
                datosPersona.setApPrimer(local.getApPrimer());
                datosPersona.setApSegundo(local.getApSegundo());
                datosPersona.setPrenombres(local.getPerNombres());
                datosPersona.setDireccion(local.getDireccion());
                datosPersona.setEstadoCivil(local.getEstadoCivil());
                datosPersona.setFoto(local.getFoto());
                datosPersona.setRestriccion(local.getRestriccion());
                datosPersona.setUbigeo(local.getUbigeo());
                returnData.setDatosPersona(datosPersona);

                ConsultarResponse consultarResponse = new ConsultarResponse();
                consultarResponse.setReturnData(returnData);

                ReniecResponseDTO responseDTO = new ReniecResponseDTO();
                responseDTO.setOrigen("BASE DE DATOS");
                responseDTO.setConsultarResponse(consultarResponse);

                // SIEMPRE registrar en historial vía SP
                PideReniec dto = mapearDTODeseLocal(local, requestDTO, req,"BASE DE DATOS");
                reniecService.registrarConsulta(dto);

                return responseDTO;
            }
            // 2. Si no está en BD, consultar RENIEC
            Map<String, Object> requestParametros = new HashMap<>();
            requestParametros.put("nuDniConsulta",requestDTO.getNumDocumento());
            requestParametros.put("nuDniUsuario",requestDTO.getDniLogin());
            requestParametros.put("nuRucUsuario",reniecConfig.getRuc());
            requestParametros.put("password",requestDTO.getDniLogin());

            Map<String, Object> requestBody = Map.of("PIDE", requestParametros);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<ReniecResponseDTO> response =
                    restTemplate.exchange(
                            reniecConfig.getUrl().getConsulta(),
                            HttpMethod.POST,
                            entity,
                            ReniecResponseDTO.class
                    );
            body = response.getBody();
        }catch (RestClientException e){
            throw new RuntimeException("Error al consumir RENIEC: " + e.getMessage(), e);
        }
        // 3. Procesar respuesta de RENIEC y registrar en BD/historial
        if (body != null &&
                body.getConsultarResponse() != null &&
                body.getConsultarResponse().getReturnData() != null) {

            ReturnData ret = body.getConsultarResponse().getReturnData();

            String codigo = ret.getCoResultado();

            if (!"0000".equals(codigo)) {
                // Error → devolver solo código + mensaje
                ReturnData soloError = new ReturnData();
                soloError.setCoResultado(codigo);
                soloError.setDeResultado(ret.getDeResultado());

                ConsultarResponse wrapper = new ConsultarResponse();
                wrapper.setReturnData(soloError);

                ReniecResponseDTO errorResponse = new ReniecResponseDTO();
                errorResponse.setConsultarResponse(wrapper);
                return errorResponse;
            }
            DatosPersona persona = ret.getDatosPersona();
            if (persona == null) {
                throw new IllegalStateException("RENIEC devolvió 0000 pero sin datosPersona");
            }
            PideReniec dto = new PideReniec();
            dto.setNumDocumento(requestDTO.getNumDocumento());
            dto.setApPrimer(persona.getApPrimer());
            dto.setApSegundo(persona.getApSegundo());
            dto.setPerNombres(persona.getPrenombres());
            dto.setDireccion(persona.getDireccion());
            dto.setEstadoCivil(persona.getEstadoCivil());
            dto.setFoto(persona.getFoto());
            dto.setRestriccion(persona.getRestriccion());
            dto.setUbigeo(persona.getUbigeo());
            dto.setIpMaquina(extractClientIp(req));
            dto.setNavegador(req.getHeader("User-Agent"));
            dto.setUserLogin(requestDTO.getLogin());
            dto.setDniAccesoReniec(requestDTO.getDniLogin());
            dto.setModulo(requestDTO.getModulo());
            dto.setPlataforma(requestDTO.getPlataforma());
            dto.setConsulta("RENIEC");
            try {
                ReniecRegistroResponseDTO result = reniecService.registrarConsulta(dto);
                //log.info("Registro BD: {} - {}", result.getStatus(), result.getMessage());
                log.info("Registro BD");
            } catch (Exception e) {
                //log.error("Error registrando en BD para DNI {}", requestDTO.getNumDocumento(), e);
                log.error("Error registrando en BD para DNI");
            }

            log.info("Consulta RENIEC exitosa");
        }

        return body;
    }

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For","X-Real-IP","CF-Connecting-IP","True-Client-IP","Forwarded"};
        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private PideReniec mapearDTODeseLocal(DatosPersonaLocalDTO local, ReniecRequestDTO requestDTO, HttpServletRequest req, String consulta) {
        PideReniec dto = new PideReniec();
        dto.setNumDocumento(local.getNumDocumento());
        dto.setApPrimer(local.getApPrimer());
        dto.setApSegundo(local.getApSegundo());
        dto.setPerNombres(local.getPerNombres());
        dto.setDireccion(local.getDireccion());
        dto.setEstadoCivil(local.getEstadoCivil());
        dto.setFoto(local.getFoto());
        dto.setRestriccion(local.getRestriccion());
        dto.setUbigeo(local.getUbigeo());
        dto.setIpMaquina(extractClientIp(req));
        dto.setNavegador(req.getHeader("User-Agent"));
        dto.setUserLogin(requestDTO.getDniLogin());
        dto.setDniAccesoReniec(requestDTO.getDniLogin());
        dto.setModulo(requestDTO.getModulo());
        dto.setPlataforma(requestDTO.getPlataforma());
        dto.setConsulta(consulta);
        return dto;
    }



}
