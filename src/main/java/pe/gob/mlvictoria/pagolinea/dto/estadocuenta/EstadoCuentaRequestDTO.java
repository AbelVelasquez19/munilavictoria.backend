package pe.gob.mlvictoria.pagolinea.dto.estadocuenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaRequestDTO {
    private int buscar;
    private String codigo;
    private int resumen;
    private int detalle;
    private int agrupar;
    private String annos;
    private String tipos;
    private String tiporec;
    private String perio;
    private String predio;
    private String estado;
    private int criterio;
    private String idrecibo;
}
