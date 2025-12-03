package pe.gob.mlvictoria.talleres.dto.apoderado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoRequest {
    private Integer opcion;
    private String codigo;
    private String idTipoDocumento;
    private String numDocumento;
    private String operador;
    private String estacion;
    private String password;
}
