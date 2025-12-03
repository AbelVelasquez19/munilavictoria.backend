package pe.gob.mlvictoria.pagolinea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecuperarContrasenaResponseDTO {

    @JsonProperty("rescode")
    private String rescode;

    @JsonProperty("message")
    private int message;

    @JsonProperty("correo_enmascarado")
    private String correoEnmascarado;

    @JsonProperty("email")
    private String email;
}
