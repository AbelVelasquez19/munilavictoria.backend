package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GenerarReciboRequestDTO {
    private String codigo;          // @codigo
    private String cajero;          // @cajero
    private String dxml;            // @dataxml
    private String formaPago;       // @tipo_pago
    private String criterio;        // @criterio
    private String cmbBanco;        // @banco
    private String cmbTarjeta;      // @tarjeta
    private String txtObservacion;  // @observa
    private String nomCaja;         // @operador
    private String estacion;        // @estacion
    private BigDecimal txtCobrar;   // @monto
    private BigDecimal txtEfectivo; // @montoacuenta
}
