package pe.gob.mlvictoria.talleres.dto.restablecer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestablecerResponse {
    private String codigo;
    private String numDoc;
    private String email;
    private String status;
    private String message;
    private String token;
}
