package pe.gob.mlvictoria.accesosigmun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarAccesoRequestDTO {
    private Integer opcion;
    private String idUsuario;
    private String parametro;
    private String estacion;
    private String codigo;
    private Integer idsistema;
}
