package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.Data;

@Data
public class GenerarReciboResponseDTO {
    private String codigo;
    private String numIngr;       // Número de ingreso generado
    private String codPred;
    private String anexo;
    private String subAnexo;
    private String tipo;
    private String tipoGeneral;
    private String ntotal;
    private String monto;
    private String movimiento;
    private String anno;
    private String direpredio;
    private String ordenRecibo;
}
