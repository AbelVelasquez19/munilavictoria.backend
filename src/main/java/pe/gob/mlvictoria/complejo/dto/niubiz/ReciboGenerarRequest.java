package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReciboGenerarRequest {
    private Integer msquery;
    private String codigo;
    private String anno;
    private String numDocu;
    private String tipo;
    private String tipoRec;
    private String periodo;
    private Double impInsol;
    private Double impReaj;
    private String observacion;
    private String fecVenc;
    private String operador;
    private String estacion;
}
