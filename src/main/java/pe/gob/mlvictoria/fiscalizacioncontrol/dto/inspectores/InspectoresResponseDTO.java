package pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InspectoresResponseDTO {
    private String FAANOTRIBU;
    private String FACODINSPE;
    private String FANOMINSPE;
    private String FANRODOCUM;
    private String ROW;
    private Integer total;
}
