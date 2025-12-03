package pe.gob.mlvictoria.fiscalizacioncontrol.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.giros.GirosResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.infraccion.InfraccionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.inspectores.InspectoresResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ListasRespondeDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ReporteNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listas.ReporteNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.multas.CalificaMultaResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.numeronotificacion.NumeroNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarnotificacion.RegistrarNotificacionResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.repository.CalificaMultaRepository;
import pe.gob.mlvictoria.fiscalizacioncontrol.repository.RegistrarNotificacionRepository;
import pe.gob.mlvictoria.fiscalizacioncontrol.service.CalificaMultaService;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class CalificaMultaServiceImpl implements CalificaMultaService {
    @Autowired
    private final CalificaMultaRepository calificaMultaRepository;
    private final RegistrarNotificacionRepository registrarNotificacionRepositry;
    @Autowired
    private DataSource dataSource;

    @Override
    public List<CalificaMultaResponseDTO> listarCalificaMulta(CalificaMultaRequestDTO dto) {
        try{
            return calificaMultaRepository.listarCalificaMulta(dto);
        } catch (Exception e) {
            log.error("Error al listar califica multa: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ViasResponseDTO> listarVias(ViasRequestDTO dto) {
        try{
            return calificaMultaRepository.listarVias(dto);
        } catch (Exception e) {
            log.error("Error al listar vias: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<GirosResponseDTO> listarGiros(GirosRequestDTO dto) {
        try{
            return calificaMultaRepository.listarGiros(dto);
        } catch (Exception e) {
            log.error("Error al listar giros: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<InspectoresResponseDTO> listarInspector(InspectoresRequestDTO dto) {
        try{
            return calificaMultaRepository.listarInspector(dto);
        } catch (Exception e) {
            log.error("Error al listar inspector: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ListasRespondeDTO> listas(ListasRequestDTO dto) {
        try{
            return calificaMultaRepository.listas(dto);
        } catch (Exception e) {
            log.error("Error al listar listas: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<InfraccionResponseDTO> listasInfraccion(InfraccionRequestDTO dto) {
        try{
            return calificaMultaRepository.listaInfraccion(dto);
        } catch (Exception e) {
            log.error("Error al listar infraccion: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public RegistrarNotificacionResponseDTO  registrarNotificacion(RegistrarNotificacionRequestDTO dto) {
        try{
            return registrarNotificacionRepositry.registrarNotificacion(dto);
        } catch (Exception e) {
            log.error("Error al registrar notificacion: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public NumeroNotificacionResponseDTO numeroNotificacion(NumeroNotificacionRequestDTO dto) {
        try{
            return calificaMultaRepository.numeroNotificacion(dto);
        } catch (Exception e) {
            log.error("Error al registrar notificacion: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public byte[] imprimirNotificacionCargo(ReporteNotificacionRequestDTO dto) {
        try {
            if (dto.getFecIniNoti() == null || dto.getFecFinNoti() == null || dto.getInspector() == null) {
                throw new IllegalArgumentException("Los parámetros fecIniNoti, fecFinNoti e inspector son obligatorios");
            }

            List<ReporteNotificacionResponseDTO> registros = calificaMultaRepository.imprimirCargoActa(dto);
            if (registros == null || registros.isEmpty()) {
                throw new IllegalArgumentException("No hay registros para el rango de fechas seleccionados.");
            }

            InputStream jasperStream = new ClassPathResource("reportes/notificacion/reporteNotificacionesMaestroApp.jasper")
                    .getInputStream();

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fecIniNoti", dto.getFecIniNoti());
            parametros.put("fecFinNoti", dto.getFecFinNoti());
            parametros.put("inspector", dto.getInspector());
            parametros.put("REPORT_DIR", "reportes/notificacion/");


            Connection conexion = dataSource.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, conexion);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (JRException e) {
            throw new RuntimeException("Error al generar el reporte Jasper: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ReporteNotificacionResponseDTO> imprimirCargoActa(ReporteNotificacionRequestDTO dto) {
        try{
            return calificaMultaRepository.imprimirCargoActa(dto);
        } catch (Exception e) {
            log.error("Error al listar para imprimir: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
