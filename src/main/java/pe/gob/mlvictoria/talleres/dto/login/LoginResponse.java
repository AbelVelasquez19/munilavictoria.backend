package pe.gob.mlvictoria.talleres.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer status;
    private String message;
    private Integer idUsuario;
    private String dni;
    private String passwordHash;
    private String nombreCompleto;
    private String correo;
    private Integer idRol;
    private String rolNombre;
    private String fechaLogin;
}
