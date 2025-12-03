package pe.gob.mlvictoria.pagolinea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponseDTO {
    private String codigo;
    private String dniruc;
    private String num_doc;
    private String nombres;
    private String token;
    private String status;

}
