package pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfraccionRequestDTO {
    private Integer busc;
    private Integer inicio;
    private Integer fin;
    private String origen;
    private String lineaaccion;
    private String desc;
    private String ordenanza;
}