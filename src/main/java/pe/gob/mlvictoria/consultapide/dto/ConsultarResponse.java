package pe.gob.mlvictoria.consultapide.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConsultarResponse {
    @JsonProperty("return")
    private ReturnData returnData;
}
