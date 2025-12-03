package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionRequestDTO {
    private int buscar;
    private String numIngr;
    private String codigo;
    private String purchaseNumber;
    private String currency;
    private String transactionDate;
    private String terminal;
    private String actionCode;
    private String traceNumber;
    private String eciDescription;
    private String eci;
    private String signature;
    private String brand;
    private String card;
    private String merchant;
    private String status;
    private String adquirente;
    private String actionDescription;
    private String idUnico;
    private BigDecimal amount;
    private String processCode;
    private String transactionId;
    private String authorizationCode;
}
