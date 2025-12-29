package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class ObtUsuarioTallerResponse {
    private Integer status;
    private String message;
    private Integer idUsuario;
    private String username;
    private String estado;
    private String codigo;
    private String tipoDocumento;
    private String numDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private Integer idRol;
    private String nombreRol;
}
