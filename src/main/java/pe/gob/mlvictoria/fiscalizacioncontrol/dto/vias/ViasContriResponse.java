package pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViasContriResponse {
    private String codVia;
    private String idZona;
    private String nomZona;
    private String idUrba;
    private String nombabr;
    private String nombres;
    private String tipoabr;
    private String nombreVia;
    private Integer arancel;
    private Integer cuaini;
    private Integer cuafin;
    private String parimp;
    private Integer row;
    private Integer total=null;
}
