package pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViasRequestDTO {
    private Integer busc;
    private Integer inicio;
    private Integer fin;
    private String desc;
}
