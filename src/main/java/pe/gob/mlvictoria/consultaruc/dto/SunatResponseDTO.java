package pe.gob.mlvictoria.consultaruc.dto;

import lombok.Data;

@Data
public class SunatResponseDTO {
    private String numRuc;
    private String nombre;
    private String estado;
    private String condicion;
    private String tipoContribuyente;
    private String direccion;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;
}
