package pe.gob.mlvictoria.consultapide.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReniecResponseDTO {
    @JsonProperty("consultarResponse")
    private ConsultarResponse consultarResponse;
    private String origen = "RENIEC";
}