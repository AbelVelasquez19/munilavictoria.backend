package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.Data;

@Data
public class DetalleReservaResponse {
    private Integer status;
    private String message;
    private Integer idDetalle;
    private Integer idReserva;
    private Integer idHorarioBase;
    private String rango;
    private Integer precio;
    private String codTasa;
    private String tipoTasa;
}
