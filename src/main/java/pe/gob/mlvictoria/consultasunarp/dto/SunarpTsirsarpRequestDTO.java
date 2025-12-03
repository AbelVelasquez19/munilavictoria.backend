package pe.gob.mlvictoria.consultasunarp.dto;

import lombok.Data;

@Data
public class SunarpTsirsarpRequestDTO {
    private String tipoParticipante;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private String razonSocial;
}
