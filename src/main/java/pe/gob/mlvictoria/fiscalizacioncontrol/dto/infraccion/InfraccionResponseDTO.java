package pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfraccionResponseDTO {
    private String FANROVIGEN;
    private String FACODMULTA;
    private String FASUBMULTA;
    private String FACODAREA;
    private String FADESMULTA;
    private Integer FNPORCENTA;
    private String FACODBASE;
    private String tipo;
    private String FAANOTRIBU;
    private Integer ROW;
    private Integer total;
}
