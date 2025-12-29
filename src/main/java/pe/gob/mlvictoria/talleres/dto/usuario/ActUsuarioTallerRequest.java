package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class ActUsuarioTallerRequest {
    private Integer idUsuario;
    private Integer idRol;
    private String passwordHash;
    private String estado;
    private String operador;
    private String estacion;
    private String codigo;
}
