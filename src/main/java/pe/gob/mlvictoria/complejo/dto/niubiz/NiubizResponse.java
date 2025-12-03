package pe.gob.mlvictoria.complejo.dto.niubiz;

import lombok.Data;

@Data
public class NiubizResponse {
    private Integer idToken;
    private String accessToken;
    private String transactionToken;
    private Double amount;
    private String purchasNumber;
    private Integer estado;
    private String estadoLetra;
    private String fechaRegistro;
    private String status;
    private String message;
}
