package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class SessionRequestDTO {
    private String channel;
    private BigDecimal amount;
    private Antifraud antifraud;
    private Map<String, Object> dataMap;

    @Data
    public static class Antifraud {
        private String clientIp;
        private Map<String, Object> merchantDefineData; // MDD4, MDD32, MDD75, MDD77...
    }
}
