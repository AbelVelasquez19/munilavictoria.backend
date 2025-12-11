package pe.gob.mlvictoria.complejo.dto.adminlogin;

import lombok.Data;

@Data
public class AdminComResulLoginResponse {
    private String numDoc;
    private String nombres;
    private String paterno;
    private String materno;
    private String token;
}
