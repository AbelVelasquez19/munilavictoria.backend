package pe.gob.mlvictoria.talleres.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String numDocumento;
    private String password;
}
