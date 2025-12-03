package pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumeroNotificacionRequestDTO {
    private Integer busc;
    private String anno;
    private Integer idordenanza;
    private String area;
}
