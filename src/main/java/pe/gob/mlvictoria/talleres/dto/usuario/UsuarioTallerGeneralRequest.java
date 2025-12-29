package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class UsuarioTallerGeneralRequest {
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String correo;
    private String password;
    private String repetirPassword;
    private Integer idRol;
}
