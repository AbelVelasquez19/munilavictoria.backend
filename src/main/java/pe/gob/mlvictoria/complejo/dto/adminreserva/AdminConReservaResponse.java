package pe.gob.mlvictoria.complejo.dto.adminreserva;

import lombok.Data;

@Data
public class AdminConReservaResponse {
    private Integer idEstadoCancha;
    private String fecha;
    private Integer idCancha;
    private String nombreCancha;
    private String nombreComplejo;
    private Integer idHorarioBase;
    private String rango;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private Integer idReserva;
    private Double montoTotal;
    private Integer idRecibo;
    private String estadoLetra;
    private String clienteNombre;
    private String clienteDocumento;
    private String estadoNiubiz;
    private String purchase_number;
    private Double precioTarifa;
}
