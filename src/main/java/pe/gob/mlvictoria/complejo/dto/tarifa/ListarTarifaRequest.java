package pe.gob.mlvictoria.complejo.dto.tarifa;

import lombok.Data;

@Data
public class ListarTarifaRequest {
    private Integer idComplejo;
    private Integer idCancha;
    private Integer diaSemana;
    private String tipoHorario;
    private Integer estado;
    private Integer numeroPagina;
    private Integer totalPagina;
}
