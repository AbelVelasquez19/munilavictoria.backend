package pe.gob.mlvictoria.accesosigmun.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logout {

    private String idUsuario;

    private String vlogin;
    private String area;
    private Integer cajero;
    private String idDoc;
    private String numDoc;
    private String nombre;
    private String caja;
    private Integer nestado;
    private String idPerfil;
    private String nombPerfil;
    private String nombArea;
    private String encargado;
    private String childaccess;
    private String ubicaRentas;
    private Integer idUnidadOrganica;

    private String token;
}
