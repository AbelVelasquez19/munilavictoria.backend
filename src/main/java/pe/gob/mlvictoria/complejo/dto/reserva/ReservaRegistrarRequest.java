package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ReservaRegistrarRequest {
    private Integer idAdministrado;
    private Integer idCancha;
    private String fecha;
    private String horarios; // ej. '1,2,3'
    private Double montoTotal;
    private Integer cantidadHoras;
    private String operador;
    private String estacion;
}
