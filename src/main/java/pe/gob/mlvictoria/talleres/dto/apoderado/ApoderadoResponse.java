package pe.gob.mlvictoria.talleres.dto.apoderado;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApoderadoResponse {
    private String status;
    private String message;
    private String idApoderado;
    private String correo;
}
