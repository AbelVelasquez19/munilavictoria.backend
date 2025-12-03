package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaResponse {
    private String idrecibo;
    private String codigo;

    private String tipo;
    private Integer anno;

    private String codPred;
    private String anexo;
    private String subAnexo;
    private String tipoDocu;
    private String numDocu;
    private String tipoRec;
    private String periodo;

    private BigDecimal impInsol;
    private BigDecimal costoEmis;

    private BigDecimal factReaj;
    private BigDecimal impReaj;

    private BigDecimal factMora;
    private BigDecimal mora;
    private String observacion;
    private Integer estado;
    private String ubica2;

    private LocalDateTime fecVenc;

    private String numIngr;
    private String operador;
    private String estacion;

    private LocalDateTime fechIng;
    private String ubica;
    private LocalDateTime fecPago;

    private String desTipo;
    private Integer criterio;
    private BigDecimal descuento;
    private String usuario;

    private BigDecimal descInsol;
    private BigDecimal descReaj;
    private BigDecimal descMora;
    private BigDecimal descEmis;

    private BigDecimal tasaCambioVenta;
}
