package pe.gob.mlvictoria.pagolinea.dto.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private String sessionKey;
    private String purchaseNumber;
    private String amount;
    private String merchantId;
}
