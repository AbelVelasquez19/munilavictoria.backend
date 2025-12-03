package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResultadoResponse {
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
    private List<ReservaHistorialResponse> detalles;
}
