package pe.gob.mlvictoria.complejo.dto.adminreserva;

import lombok.Data;

@Data
public class CambiarEstadoHorarioRequest {
    private Integer idEstadoCancha;
    private String nuevoEstado;
    private String operador;
    private String estacion;
}
