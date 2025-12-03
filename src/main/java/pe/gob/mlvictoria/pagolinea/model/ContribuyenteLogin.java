package pe.gob.mlvictoria.pagolinea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContribuyenteLogin {
    private String codigo;
    private String password;
    private String dniruc;
    private String num_duc;
    private String nombres;
    private String token;
}
