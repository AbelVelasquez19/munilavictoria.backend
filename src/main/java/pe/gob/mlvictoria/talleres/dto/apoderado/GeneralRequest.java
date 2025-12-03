package pe.gob.mlvictoria.talleres.dto.apoderado;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralRequest {
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String paterno;
    private String materno;
    private String telefono;
    private String correo;
    private String password;
    private String repetirPassword;
}
