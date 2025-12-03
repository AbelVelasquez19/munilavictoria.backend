package pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarNotificacionRequestDTO {
    private Integer busc;
    private String FATIPNOTIF;
    private String FAANONOTIF;
    private String FANRONOTIF;
    private String FACODAREA;
    private String FACODCONTR;
    private String FATIPVIA;
    private String FACODVIA;
    private String FANUMERO;
    private String FADEPARTAM;
    private String FAMANZANA;
    private String FALOTE;
    private String FAREFERENC;
    private String FATELEFONO;
    private String FAHORNOTIF;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate FDFECNOTIF;

    private String FACODGIRO;
    private String FANROVIGEN;
    private String FACODMULTA;
    private BigDecimal FNTOTAL;
    private String FACODINSPE;
    private String FACALNOTIF;
    private String FMOBSERVAC;
    private String FAOPERADOR;
    private String FAESTACION;
    private String FAPRESENTO;
    private String FADOCUMENTO;
    private String FAANODOCUM;
    private String FACODAREAINF;
    private String ZONIFICACION;
    private String FASUBMULTA;
    private Integer FNCANTIDAD;
    private BigDecimal FNIMPMULTA;
    private String FACODBASE;
    private String FACODCONTI;
    private String FAANONOTIFC;
    private String FANRONOTIFC;
    private String FACODLINEA;
    private String FATIPVIA2;
    private String FACODVIA2;
    private String FANUMERO2;
    private String FADEPARTAM2;
    private String FAMANZANA2;
    private String FALOTE2;
    private String FAREFERENC2;
    private String FACODDISTR;
    private String FANOMVIA;
    private String tipo;
    private String NROIFI;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate FECHIFI;

    private Integer NRONOTIF;
    private Integer idordenanza;
    private String area;

    private String latitud;
    private String longitud;

    private String fotoFrontal;
    private String fotoPlaca;
    private String video;

    private String placa;
    private String marca;
    private String color;

}
