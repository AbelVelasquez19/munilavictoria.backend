package pe.gob.mlvictoria.consultapide.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnData {
    private String coResultado;
    private String deResultado;

    @JsonProperty("datosPersona")
    private DatosPersona datosPersona;
}
