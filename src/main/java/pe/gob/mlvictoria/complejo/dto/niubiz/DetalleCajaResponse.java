package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCajaResponse {
    private String numIngr;
    private int idrecibo;
    private BigDecimal montoTotal;
    private String codigo;
    private int anno;
    private String codPred;
    private String anexo;
    private String subAnexo;
    private String tipo;
    private String tipoRec;
    private String periodo;
    private BigDecimal impInsol;
    private BigDecimal factReaj;
    private BigDecimal impReaj;
    private BigDecimal factMora;
    private BigDecimal impMora;
    private BigDecimal costoEmis;
    private String observacion;
    private String operador;
    private String estacion;
    private String fechaIngreso;
    private BigDecimal descuento;
    private String ubica;
    private BigDecimal descInsol;
    private BigDecimal descReaj;
    private BigDecimal descMora;
    private BigDecimal descEmis;
}
