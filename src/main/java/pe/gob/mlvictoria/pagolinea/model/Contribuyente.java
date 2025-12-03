package pe.gob.mlvictoria.pagolinea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contribuyente {
    private String codigo;
    private String tipoNumDocu;
    private String numDocu;
    private String email;
    private String numIngrValidacion;
    private String codigoConfirmacion;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private String telefono;
    private String recuperar;
    private String correoEnmascarado;
}
