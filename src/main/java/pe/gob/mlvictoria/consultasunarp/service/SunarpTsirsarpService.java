package pe.gob.mlvictoria.consultasunarp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pe.gob.mlvictoria.consultasunarp.dto.ApiResponse;
import pe.gob.mlvictoria.consultasunarp.dto.SunarpTsirsarpRequestDTO;
import pe.gob.mlvictoria.consultasunarp.dto.SunarpTsirsarpResponseDTO;
import pe.gob.mlvictoria.utility.SunarpConfig;

import java.time.LocalDateTime;
import java.util.*;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@Slf4j
@Service
@AllArgsConstructor
public class SunarpTsirsarpService {

    private final OkHttpClient client = new OkHttpClient();
    private final SunarpConfig sunarpConfig;

    public ApiResponse<List<SunarpTsirsarpResponseDTO>> consultarSunarp(SunarpTsirsarpRequestDTO dto) {
        try {
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

            Map<String, Object> payload = Map.of(
                    "PIDE", Map.of(
                            "usuario", sunarpConfig.getUsuario(),
                            "clave", sunarpConfig.getClave(),
                            "tipoParticipante", dto.getTipoParticipante(),
                            "apellidoPaterno", dto.getApellidoPaterno(),
                            "apellidoMaterno", dto.getApellidoMaterno(),
                            "nombres", dto.getNombres(),
                            "razonSocial", dto.getRazonSocial()
                    )
            );

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(payload);

            RequestBody body = RequestBody.create(json, mediaType);

            Request request = new Request.Builder()
                    .url(sunarpConfig.getUrl().getTsirsarp())
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return new ApiResponse<>(
                            LocalDateTime.now(),
                            "ERROR",
                            "Error HTTP: " + response.code(),
                            Collections.emptyList()
                    );
                }

                String raw = response.body().string();
                JsonNode root = mapper.readTree(raw);

                if (root.has("Respuesta") && root.get("Respuesta").has("Error")) {
                    String errorMsg = root.get("Respuesta").get("Error").asText();
                    log.warn("SUNARP devolvió error: {}", errorMsg);
                    return new ApiResponse<>(
                            LocalDateTime.now(),
                            "ERROR",
                            errorMsg,
                            Collections.emptyList()
                    );
                }

                JsonNode lista = root
                        .path("buscarTitularidadSIRSARPResponse")
                        .path("respuestaTitularidad")
                        .path("respuestaTitularidad");

                List<SunarpTsirsarpResponseDTO> resultados = new ArrayList<>();

                if (lista.isArray()) {
                    for (JsonNode item : lista) {
                        resultados.add(mapToDto(item));
                    }
                }
                else if (lista.isObject()) {
                    resultados.add(mapToDto(lista));
                }
                else {
                    return new ApiResponse<>(
                            LocalDateTime.now(),
                            "ERROR",
                            "No existen registros para los datos consultados",
                            Collections.emptyList()
                    );
                }

                return new ApiResponse<>(
                        LocalDateTime.now(),
                        "OK",
                        "Consulta SUNARP exitosa",
                        resultados
                );
            }

        } catch (Exception e) {
            log.error("Error al consumir SUNARP TSIRSARP", e);
            return new ApiResponse<>(
                    LocalDateTime.now(),
                    "ERROR",
                    "Excepción en la consulta: " + e.getMessage(),
                    Collections.emptyList()
            );
        }
    }

    private SunarpTsirsarpResponseDTO mapToDto(JsonNode item) {
        SunarpTsirsarpResponseDTO dto = new SunarpTsirsarpResponseDTO();
        dto.setRegistro(item.path("registro").asText(null));
        dto.setLibro(item.path("libro").asText(null));
        dto.setApPaterno(item.path("apPaterno").asText(null));
        dto.setApMaterno(item.path("apMaterno").asText(null));
        dto.setNombre(item.path("nombre").asText(null));
        dto.setRazonSocial(item.path("razonSocial").asText(null));
        dto.setTipoDocumento(item.path("tipoDocumento").asText(null));
        dto.setNumeroDocumento(item.path("numeroDocumento").asText(null));
        dto.setNumeroPartida(item.path("numeroPartida").asText(null));
        dto.setNumeroPlaca(item.path("numeroPlaca").asText(null));
        dto.setEstado(item.path("estado").asText(null));
        dto.setZona(item.path("zona").asText(null));
        dto.setOficina(item.path("oficina").asText(null));
        dto.setDireccion(item.path("direccion").asText(null));
        return dto;
    }

}
