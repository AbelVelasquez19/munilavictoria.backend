package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NiubizRequest {
    private Integer opcion;
    private String accessToken;
    private String transactionToken;
    private String purchasNumber;
    private Double amount;
    private String estado;
    private Integer idToken;
}
