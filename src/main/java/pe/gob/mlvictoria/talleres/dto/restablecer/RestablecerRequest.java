package pe.gob.mlvictoria.talleres.dto.restablecer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestablecerRequest {
    private Integer opcion;
    private String correo;
    private String codigo;
    private String numDoc;
    private String tokenn;
    private String password;
}
