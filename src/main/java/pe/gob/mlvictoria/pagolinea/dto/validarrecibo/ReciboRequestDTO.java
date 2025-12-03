package pe.gob.mlvictoria.pagolinea.dto.validarrecibo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReciboRequestDTO {
    private String codigo;
    private String json;
    private String criterio;

    private String annos;
    private String tipos;
    private String tipoRec;
    private String perio;
    private String predio;
    private String usuario;
    private String idreciboGroup;
}
