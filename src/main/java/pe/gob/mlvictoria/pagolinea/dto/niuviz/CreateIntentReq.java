package pe.gob.mlvictoria.pagolinea.dto.niuviz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  CreateIntentReq {
    String purchaseNumber;
    BigDecimal amount;
    String currency;
    String codigo;
    String ordenanza;
    String usuario;
    String sessionKey;
    String ip;
    String userAgent;
    String detaDataJson;
    String moduloAgente;
}
