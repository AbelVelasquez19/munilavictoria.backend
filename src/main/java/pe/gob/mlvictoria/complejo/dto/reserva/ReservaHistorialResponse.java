package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ReservaHistorialResponse {
    private Integer idHorarioBase;
    private String Rango;
    private Double precio;
}
