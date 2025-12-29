package pe.gob.mlvictoria.complejo.dto.dashboard;

import lombok.Data;

@Data
public class DashboardIngresosResponse {
    private Double ingresosDia;
    private Integer ingresosMes;
    private Double reservasDia;
    private Double pagosRechazados;
    private String mes;
    private String nombreMes;
    private Double total;
    private String rango;
    private Double totalReservas;
    private String cancha;
    private Double totalCancha;

    private Integer idReserva;
    private String fechaPago;
    private Double montoTotal;
    private String nombreCancha;
    private String complejo;
    private String operador;
    private String estacion;

    private String dia;
    private Double totalPicos;

    private String horarioPico;
    private Double montoPico;

    private String nombreCanchaPico;
    private Double totalCanchaPico;
}
