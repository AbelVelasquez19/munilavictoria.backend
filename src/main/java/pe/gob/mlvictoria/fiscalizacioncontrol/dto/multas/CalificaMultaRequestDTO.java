package pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificaMultaRequestDTO {
    private String codigo;
    private String comen;
}
