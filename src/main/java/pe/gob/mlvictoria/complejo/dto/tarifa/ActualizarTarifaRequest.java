package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

@Data
public class ActualizarTarifaRequest {
    private Integer idTarifa;
    private Integer idCancha;
    private Integer diaSemana;
    private String tipoHorario;
    private String horaInicio;
    private String horaFin;
    private Integer precio;
    private String estado;
    private String codTasa;
    private String tipoTasa;
}
