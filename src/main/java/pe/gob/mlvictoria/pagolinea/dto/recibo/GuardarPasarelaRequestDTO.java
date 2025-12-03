package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.Data;

import java.util.Map;

@Data
public class GuardarPasarelaRequestDTO {
    private String numIngr;
    private String codigo;
    private String purchaseNumber;
    private Map<String, Object> dataMap;
}
