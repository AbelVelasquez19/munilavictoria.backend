package pe.gob.mlvictoria.complejo.dto.adminlogin;

import lombok.Data;

@Data
public class AdminComLoginResponse {
    private Integer status;
    private String message;
    private Integer idUsuario;
    private String password;
    private Integer estado;
    private String numDoc;
    private String nombres;
    private String paterno;
    private String materno;
}
