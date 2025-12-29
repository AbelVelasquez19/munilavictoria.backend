package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class UsuarioTallerResponse {
    private Integer idUsuario;
    private String documento;
    private String codigo;
    private String nombreCompleto;
    private String correo;
    private String rol;
    private String estado;
    private String totalRegistros;
}
