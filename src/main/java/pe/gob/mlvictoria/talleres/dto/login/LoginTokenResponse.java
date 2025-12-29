package pe.gob.mlvictoria.talleres.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTokenResponse {
    private String numDoc;
    private String nombreCompleto;
    private String token;
    private Integer idUsuario;
    private Integer idRol;
    private String nombreRol;
}
