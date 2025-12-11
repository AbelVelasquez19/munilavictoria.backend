package pe.gob.mlvictoria.complejo.dto.pago;

import lombok.Data;

@Data
public class HeaderResponse {
    private String ecoreTransactionUUID;
    private Long ecoreTransactionDate;
    private Integer millis;
}
