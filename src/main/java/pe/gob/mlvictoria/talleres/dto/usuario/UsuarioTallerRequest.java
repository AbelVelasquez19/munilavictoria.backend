package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class UsuarioTallerRequest {
    private String buscar;
    private Integer numeroPagina;
    private Integer totalPagina;
}
