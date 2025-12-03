package pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirosRequestDTO {
    private Integer busc;
    private Integer inicio;
    private Integer fin;
    private String desc;
}
