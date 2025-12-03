package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class AuthorizationRequestDTO {
    private String tokenId;
    private String purchaseNumber;
    private BigDecimal amount;
    private String currency = "PEN";
    private String channel = "web";
    private String captureType = "manual";
    private boolean countable = true;

    private String urlAddress;
    private String partnerIdCode;
    private Map<String, Object> dataMap;
}
