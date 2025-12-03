package pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas;

import lombok.Data;

@Data
public class ReporteNotificacionRequestDTO {
    private String fecIniNoti;
    private String fecFinNoti;
    private String inspector;
}
