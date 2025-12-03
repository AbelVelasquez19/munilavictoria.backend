package pe.gob.mlvictoria.complejo.dto.administrado;

import lombok.Data;

@Data
public class ComAdminRequest {
    private Integer opcion;
    private Integer tipoPersona;
    private Integer tipoDocumento;
    private String codigo;
    private String numDocumento;
    private Integer conAcepto;
    private Integer conNoResonsabilizar;
    private String operador;
    private String estacion;
}
