package pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion;

import lombok.Data;

@Data
public class RegistrarNotificacionResponseDTO {
    private String codigo;   // '0', '2', etc.
    private String mensaje;
}
