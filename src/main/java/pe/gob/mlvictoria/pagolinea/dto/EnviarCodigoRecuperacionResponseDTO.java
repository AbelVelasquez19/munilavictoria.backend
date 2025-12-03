package pe.gob.mlvictoria.pagolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviarCodigoRecuperacionResponseDTO {
    private String rescode;
    private String message;
    private String val;
    private String email;
    private String enfriamiento;
    private String codigo;
}
