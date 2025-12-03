package pe.gob.mlvictoria.talleres.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTokenResponse {
    private String numDoc;
    private String nombres;
    private String paterno;
    private String materno;
    private String token;
}
