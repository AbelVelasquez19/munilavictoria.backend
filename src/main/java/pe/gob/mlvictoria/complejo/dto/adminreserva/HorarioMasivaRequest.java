package pe.gob.mlvictoria.complejo.dto.adminreserva;

import lombok.Data;

@Data
public class HorarioMasivaRequest {
    private Integer idComplejo;
    private String listaCanchas;
    private String listaFechas;
    private String operador;
    private String estacion;
}
