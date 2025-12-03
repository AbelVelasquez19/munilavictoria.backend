package pe.gob.mlvictoria.pagolinea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnioPenContriRespondeDTO {
    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("msquery")
    private int msquery;

    @JsonProperty("minimo")
    private String minimo;

    @JsonProperty("maximo")
    private String maximo;
}
