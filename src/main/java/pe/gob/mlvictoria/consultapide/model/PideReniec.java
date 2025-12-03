package pe.gob.mlvictoria.consultapide.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PideReniec {
    private Integer idPideReniec;
    private String numDocumento;
    private String apPrimer;
    private String apSegundo;
    private String perNombres;
    private String direccion;
    private String estadoCivil;
    private String foto;
    private String restriccion;
    private String ubigeo;
    private String ipMaquina;
    private String navegador;
    private LocalDateTime fechaConsulta;
    private String userLogin;
    private String modulo;
    private String dniAccesoReniec;
    private String plataforma;
    private String consulta;
}
