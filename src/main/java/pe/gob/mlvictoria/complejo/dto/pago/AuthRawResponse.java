package pe.gob.mlvictoria.complejo.dto.pago;

import lombok.Data;

@Data
public class AuthRawResponse {
    private Integer errorCode;
    private String errorMessage;
    private HeaderResponse header;
    private DataSectionResponse data;
}
