package pe.gob.mlvictoria.pagolinea.dto.recibo;

import lombok.Data;

@Data
public class ImprimirReciboRequestDTO {
    private Integer buscar;
    private String codigo;
    private String numIngr;
}
