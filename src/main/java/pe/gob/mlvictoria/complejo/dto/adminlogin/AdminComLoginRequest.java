package pe.gob.mlvictoria.complejo.dto.adminlogin;

import lombok.Data;

@Data
public class AdminComLoginRequest {
    private String numDocumento;
    private String password;
}
