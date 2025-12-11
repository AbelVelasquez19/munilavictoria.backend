package pe.gob.mlvictoria.complejo.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketAproResulResponse {
    private Integer status;
    private String message;
    private Integer idReserva;
    private String nombreAdministrado;
    private String tipoDocumento;
    private String numeroDocumento;
    private String celular;
    private String correo;
    private String codigoContribuyente;
    private String complejo;
    private String cancha;
    private String fechaReserva;
    private Integer cantidadHoras;
    private Double montoTotal;
    private Integer idrecibo;
    private String numIngr;
    private String fecPago;
    private String purchaseNumber;
    private String estadoNiubiz;
    private Integer tarifaHora;
    private List<TicketDetalleResponse> detallesJson;
}
