package pe.gob.mlvictoria.talleres.dto.usuario;

import lombok.Data;

@Data
public class RegisUsuarioTallerResponse {
     private Integer status;
     private String  message;
     private Integer idUsuario;
     private String  dni;
     private String  nombreCompleto;
     private String  correo;
}
