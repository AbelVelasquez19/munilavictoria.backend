package pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirosResponseDTO {
    private String FACODGIRO;
    private String FADESGIRO;
    private String ESTADO;
    private String ROW;
    private Integer total;
}
