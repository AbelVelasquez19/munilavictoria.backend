package pe.gob.mlvictoria.pagolinea.dto.estadocuenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCuentaResponseDTO {
    private String codigo;
    private String nombres;
    private String direccion;
    private String tipo;
    private String tipode1;
    private int anno;
    private String periodo;
    private String direccionPredial;
    private String codPred;
    private String anexo;
    private String subAnexo;
    private BigDecimal impInsol;
    private BigDecimal costoEmis;
    private BigDecimal impReaj;
    private BigDecimal mora;
    private BigDecimal total;
    private BigDecimal pagado;
    private BigDecimal dcto;
    private BigDecimal saldo;
    private String fecVenc;
    private String fecEmision;
    private LocalTime horEmision;
    private String totalLetra;
    private BigDecimal totalDeuda;
    private BigDecimal totalPagado;
    private BigDecimal totalDescuento;
    private BigDecimal totalSaldo;
    private String idreciboGroup;
    private String ubica;
    private String ubicacion;
    private String numDocu;
}
