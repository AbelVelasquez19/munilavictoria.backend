package pe.gob.mlvictoria.complejo.dto.reserva;

import lombok.Data;

@Data
public class ActuReciNiubizRequest {
    private  String codigo;
    private  Integer intento;
    private  String purchaseNumber;
    private  String tokenId;
    private  String status;
    private  String ordenanza;
    private Integer idRecibo;
    private  String authRawJson;
}
