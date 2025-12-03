package pe.gob.mlvictoria.pagolinea.dto.niuviz;

import lombok.Data;

@Data
public class UpdateAfterAuthRe {
    private  Long reciboId;
    private  Integer intento;
    private  String purchase_number;
    private  String tokenId;
    private  String status;
    private  String ordenanza;
    private  String authRawJson;
}
