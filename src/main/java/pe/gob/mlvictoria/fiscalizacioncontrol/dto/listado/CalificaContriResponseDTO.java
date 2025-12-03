package pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificaContriResponseDTO {
    private String codigo;
    private String nombres;
    private String fasituacion;
    private String ingreso;
    private String documento;
    private String numDoc;
    private String distrito;
    private String total;
}

