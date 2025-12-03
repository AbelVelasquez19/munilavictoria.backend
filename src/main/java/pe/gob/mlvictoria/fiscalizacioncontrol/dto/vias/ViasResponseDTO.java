package pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViasResponseDTO {
    private String FATIPVIA;
    private String FANOMVIA;
    private String FACODVIA;
    private Integer total;
}
