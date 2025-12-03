package pe.gob.mlvictoria.accesosigmun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequestDTO {
    private Integer buscar;
    private String parametro;
    private String password;
}
