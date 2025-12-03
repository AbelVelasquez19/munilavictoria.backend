package pe.gob.mlvictoria.pagolinea.dto.historialpago;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HistorialPagoResponseDTO {
    private String anoPago;
    private String countNumIngr;
    private BigDecimal monto;
    private String fecPago;
    private String numIngr;
    private String tipoDeuda;
    private String observacion;
    private String canal;
}
