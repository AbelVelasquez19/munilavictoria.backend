package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ImprimirReciboResponseDTO {
    private Long idcajas;
    private String numIngr;
    private String codigo;
    private String nombres;
    private String caja;
    private String fecPago; // Si prefieres LocalDateTime cámbialo
    private BigDecimal monto;
    private String estado;
    private String estado_;
    private String operador;
    private String nombCajero;
    private String codigo_;
    private String numIngr_;
    private String codPred;
    private String anexo;
    private String subAnexo;
    private String anno;
    private String tipodes;
    private String periodo;
    private BigDecimal impInsol;
    private BigDecimal impReaj;
    private BigDecimal impMora;
    private BigDecimal costoEmis;
    private BigDecimal montotal;
    private String dirfis;
    private String movimiento;
    private BigDecimal descuento;
    private Integer row;
    private String tipoPago;
    private String cheque;

    private String obsCuenta = "";

    private String ordenRecibo;
    private String tipoGeneral;
    private BigDecimal ntotal;
    private Integer annoRecibo;
    private String tipo;
    private String observacion;

}


