package pe.gob.mlvictoria.consultapide.dto;

import lombok.Data;


@Data
public class DatosPersona {
    private String numDocumento; // Ojo: si RENIEC no lo devuelve, quedará null
    private String apPrimer;
    private String apSegundo;
    private String prenombres;
    private String direccion;
    private String estadoCivil;
    private String restriccion;
    private String foto;
    private String ubigeo;
}
