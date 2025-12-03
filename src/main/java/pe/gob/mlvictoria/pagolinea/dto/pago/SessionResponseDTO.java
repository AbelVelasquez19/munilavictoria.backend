package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.Data;

@Data
public class SessionResponseDTO {
    private String sessionKey;
    private String expirationTime;
}
