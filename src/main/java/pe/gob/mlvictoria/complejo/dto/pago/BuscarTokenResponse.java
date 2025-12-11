package pe.gob.mlvictoria.complejo.dto.pago;

import lombok.Data;

@Data
public class    BuscarTokenResponse {
    private Integer status;
    private String message;
    private String id;
    private String purchaseNumber;
    private String estadoPago;
    private Double amount;
    private Object  authRaw;
}
