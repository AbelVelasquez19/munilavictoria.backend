package pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListasRequestDTO {
    private String busc;
    private String valor01;
}
