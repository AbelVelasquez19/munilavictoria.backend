package pe.gob.mlvictoria.complejo.dto.complejo;

import lombok.Data;

@Data
public class ComplejoResponse {
    private Integer idComplejo;
    private String nombre;
    private String imagen;
    private String direccion;
}
