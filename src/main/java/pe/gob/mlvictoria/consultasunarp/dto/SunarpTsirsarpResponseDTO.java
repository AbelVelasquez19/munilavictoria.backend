package pe.gob.mlvictoria.consultasunarp.dto;

import lombok.Data;

@Data
public class SunarpTsirsarpResponseDTO {
    private String registro;
    private String libro;
    private String apPaterno;
    private String apMaterno;
    private String nombre;
    private String razonSocial;
    private String tipoDocumento;
    private String numeroDocumento;
    private String numeroPartida;
    private String numeroPlaca;
    private String estado;
    private String zona;
    private String oficina;
    private String direccion;
}
