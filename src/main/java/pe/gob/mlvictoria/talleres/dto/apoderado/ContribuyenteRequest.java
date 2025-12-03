package pe.gob.mlvictoria.talleres.dto.apoderado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuyenteRequest {
    private String idTipoContri;
    private String  nombres;
    private String  paterno;
    private String  materno;
    private String  sexo;
    private String correoE;
    private String idDoc;
    private String numDoc;
    private String direccion;
    private String telefono;
    private Integer valida;
    private String operador;
    private String estacion;
}
