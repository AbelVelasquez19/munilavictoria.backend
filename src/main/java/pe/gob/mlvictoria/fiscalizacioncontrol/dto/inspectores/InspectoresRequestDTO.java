package pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectoresRequestDTO {
    private Integer busc;
    private Integer inicio;
    private Integer fin;
    private String FANRODOCUM;
    private String FANOMINSPE;
    private String FAANOTRIBU;
}
