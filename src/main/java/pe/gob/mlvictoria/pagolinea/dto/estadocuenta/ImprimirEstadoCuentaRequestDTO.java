package pe.gob.mlvictoria.pagolinea.dto.estadocuenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImprimirEstadoCuentaRequestDTO {
    private String codigo;     // Requerido
    private String annos;      // Opcional
    private String tipos;      // Opcional
    private String idrecibo;     // Opcional
    private String criterio;

    // Parámetros fijos si los deseas incluir directamente
    private int buscar = 1;
    private String resumen = "1";
    private String detalle = "0";
    private String agrupar = "1";
    private String tiporec = "";
    private String perio = "";
    private String predio = "";
    private String estado = "0";
    ;
}
