package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class RegisUsuarioTallerRequest {
    private String codigo;
    private String username;
    private String passwordHash;
    private Integer idRol;
    private String operador;
    private String estacion;
}
