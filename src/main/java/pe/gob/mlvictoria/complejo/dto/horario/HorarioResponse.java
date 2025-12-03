package pe.gob.mlvictoria.complejo.dto.horario;

import lombok.Data;

@Data
public class HorarioResponse {
    private Integer status;
    private String message;
    private Integer idHorarioBase;
    private String rango;
    private String estado;
}
