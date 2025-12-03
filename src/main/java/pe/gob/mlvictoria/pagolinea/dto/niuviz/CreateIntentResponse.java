package pe.gob.mlvictoria.pagolinea.dto.niuviz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateIntentResponse {
    private Long rid;
    private String purchaseNumber;
    private BigDecimal amount;
    private String currency;
}
