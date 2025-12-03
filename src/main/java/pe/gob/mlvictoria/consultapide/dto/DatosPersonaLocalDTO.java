package pe.gob.mlvictoria.consultapide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosPersonaLocalDTO {
    private String numDocumento; // Ojo: si RENIEC no lo devuelve, quedará null
    private String apPrimer;
    private String apSegundo;
    private String PerNombres;
    private String direccion;
    private String estadoCivil;
    private String restriccion;
    private String foto;
    private String ubigeo;
}
