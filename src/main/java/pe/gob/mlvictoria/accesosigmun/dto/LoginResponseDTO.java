package pe.gob.mlvictoria.accesosigmun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String IdUsuario;
    private String token;
    private String nombre;
    private String usuario;
}
