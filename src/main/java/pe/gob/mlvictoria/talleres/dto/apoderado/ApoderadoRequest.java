package pe.gob.mlvictoria.talleres.dto.apoderado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoRequest {
    private String codigo;
    private String tipoDocumento;
    private String numDocumento;
    private String passwordHash;
    private String operador;
    private String estacion;
}
