package pe.gob.mlvictoria.consultasunarp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SunarpTsirsarpPayloadDTO {


        private String usuario;
        private String clave;
        private String tipoParticipante;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String nombres;
        private String razonSocial;

}
