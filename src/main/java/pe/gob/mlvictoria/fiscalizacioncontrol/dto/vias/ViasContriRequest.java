package pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViasContriRequest {
    private Integer msquery;
    private String codVia;
    private String anno;
    private String idUrba;
    private String idVias;
    private String tipovia;
    private String vcuadra;
    private String idZona;
    private String nombabr;
    private String nombres;
    private String nomZona;
    private String tipoabr;
    private String nombreVia;
    private Double arancel;
    private Integer inicio;
    private Integer finall;
}
