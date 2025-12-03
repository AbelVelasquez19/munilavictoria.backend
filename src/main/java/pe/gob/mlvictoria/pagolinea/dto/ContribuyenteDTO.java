package pe.gob.mlvictoria.pagolinea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuyenteDTO {

    @JsonProperty("opcion")
    private int opcion;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("tipo_num_docu")
    private String tipoNumDocu;

    @JsonProperty("num_docu")
    private String numDocu;

    @JsonProperty("email")
    private String email;

    @JsonProperty("num_ingr_validacion")
    private String numIngrValidacion;

    @JsonProperty("codigo_confirmacion")
    private String codigoConfirmacion;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apellidop")
    private String apellidoP;

    @JsonProperty("apellidom")
    private String apellidoM;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("recuperar")
    private String recuperar;

    @JsonProperty("val")
    private String val;


}
