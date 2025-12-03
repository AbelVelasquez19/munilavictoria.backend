package pe.gob.mlvictoria.pagolinea.dto.estadocuenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImprimirEstadoCuentaResponseDTO {
    private String codigo;
    private String nombre;
    private String direccion;
    private String tipo;
    private String tipode1;
    private String anno;
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
    private String horEmision;
    private String totalLetra;
    private BigDecimal totalDeuda;
    private BigDecimal totalPagado;
    private BigDecimal totalDescuento;
    private BigDecimal totalSaldo;
    private String idreciboGroup;
    private String ubica;
    private String ubicacion;
    private String numDocu;

    public String getDescripcionCompleta() {
        String tipode1 = this.tipode1 != null ? this.tipode1 : "";
        String numDocu = (this.numDocu != null && !this.numDocu.trim().isEmpty()) ? this.numDocu : "";
        String direccion = (this.direccionPredial !=null && !this.direccionPredial.trim().isEmpty()) ?this.direccionPredial:"";

        return " "+tipode1 + " " + numDocu + " " + direccion;
    }

}
