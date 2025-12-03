package pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificaMultaResponseDTO {
    private String comen;
    private String faanonotif;
    private String fanronotif;
    private String nroifi;
    private String ANNOIFI;
    private String faanomulta;
    private String fanromulta;
    private String fnimpmulta;
    private String fadessitua;
    private String fadesestad;
    private String pendiente;
    private String pagado;
    private String descIsoluto;
    private Double costaNoPec;
    private String marrecla;
    private String tiprecla;
    private String estrecla;
    private Double descInsoluto;
}
