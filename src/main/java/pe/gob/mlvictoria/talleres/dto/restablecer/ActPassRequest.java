package pe.gob.mlvictoria.talleres.dto.restablecer;

import lombok.Data;

@Data
public class ActPassRequest {
    private String token;
    private String password;
    private String confirmPassword;
}
