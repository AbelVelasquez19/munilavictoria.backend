package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.Data;

import java.time.Instant;

@Data
public class ReciboDataMapResponseDTO {
    private String authJson;          // guarda el JSON completo (opcional)
    private String dataMapJson;
    private String status;
    private String actionCode;        // "000"
    private String actionDescription; // "Aprobado..."
    private String authorizationCode; // "010152"
    private String rrn;               // "919600"
    private String transactionId;     // "99125..."
    private String brand;             // "visa"
    private String cardMasked;        // "455170******8059"
    private Instant authorizedAt;
}
