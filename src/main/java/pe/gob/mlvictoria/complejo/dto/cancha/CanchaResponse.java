package pe.gob.mlvictoria.complejo.dto.cancha;

import lombok.Data;

@Data
public class CanchaResponse {
    private Integer idCancha;
    private Integer idComplejo;
    private String nombre;
    private String descripcion;
    private Integer estado;
}
