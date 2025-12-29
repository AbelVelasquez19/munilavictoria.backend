package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

import java.sql.Time;

@Data
public class ListarTarrifaResponse {
    private Integer status;
    private String message;
    private Integer idTarifa;
    private Integer idComplejo;
    private Integer idCancha;
    private String nombreComplejo;
    private String nombreCancha;
    private Integer diaSemana;
    private String diaDescripcion;
    private String turno;
    private String turnoDescripcion;
    private String rangoHorario;
    private Integer precio;
    private String tipoTasa;
    private String codTasa;
    private String estado;
    private String estadoDescripcion;
    private String fechaRegistro;
    private Integer totalRegistros;
    private Time horaInicio;
    private Time horaFin;
}
