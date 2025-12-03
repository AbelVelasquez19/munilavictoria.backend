package pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificaContriRequestDTO {
    private Integer busc;
    private Integer inicio;
    private Integer finalP;
    private Integer codigo;
    private String docu;
    private String nombre;
    private String notif;
    private String multa;
}
