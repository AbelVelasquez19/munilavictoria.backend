package pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReporteNotificacionResponseDTO {
    private String codigoContri;
    private String faanonotif;
    private String numeroNotificacion;
    private Integer nronotif;
    private String tipoDocumento;
    private String nombreAdministrado;
    private String numeroDocumento;
    private String domicilio;
    private String distrito;
    private String direccion;
    private String giro;
    private String fechaDeteccion;
    private String fechaDeteccionDia;
    private String fechaDeteccionMes;
    private String fechaDeteccionAnio01;
    private String fechaDeteccionAnio02;
    private String fechaDeteccionAnio03;
    private String fechaDeteccionAnio04;
    private String hora;
    private BigDecimal uit;
    private BigDecimal monto;
    private String medidacomp;
    private String baseLegal;
    private String inspector;
    private String tdocinspe;
    private String dniInspector;
    private String codigo;
    private String descripcion;
}
