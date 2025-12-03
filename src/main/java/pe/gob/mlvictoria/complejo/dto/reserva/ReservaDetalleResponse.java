package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ReservaDetalleResponse {
    private Integer status;
    private String message;
    private Integer idReserva;
    private Integer codigo;
    private String nombreAdministrado;
    private String tipoDocumento;
    private String numeroDocumento;
    private String celular;
    private String correo;
    private String complejo;
    private String cancha;
    private String fechaReserva;
    private Integer cantidadHoras;
    private Double montoTotal;
    private Integer tarifaHora;
    private String detallesJson;
}
