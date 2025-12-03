package pe.gob.mlvictoria.pagolinea.service.Impl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.EstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.estadocuenta.ImprimirEstadoCuentaResponseDTO;
import pe.gob.mlvictoria.pagolinea.repository.EstadoCuentaRepository;
import pe.gob.mlvictoria.pagolinea.service.EstadoCuentaService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

    @Autowired
    private EstadoCuentaRepository repository;

    @Override
    public List<EstadoCuentaResponseDTO> listarEstadoCuenta(EstadoCuentaRequestDTO requestDTO) {
        return repository.listarEstadoCuenta(requestDTO);
    }

    @Override
    public byte[] imprimirEstadoCuenta(ImprimirEstadoCuentaRequestDTO dto) {
        try {
            if(dto.getCodigo()==null || dto.getCodigo().trim().isEmpty()){
                throw  new IllegalArgumentException("El parámetro codigo es obligatorio");
            }

            List<ImprimirEstadoCuentaResponseDTO> datos = repository.imprimirEstadoCuenta(dto);
            if(datos==null || datos.isEmpty()){
                throw  new IllegalArgumentException("No hay datos disponibles para generar el reporte");
            }

            //cargar el archivo jasper
            InputStream jasperStream = new ClassPathResource("reportes/pagolinia/EstadoCuenta.jasper").getInputStream();

            //fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);

            //parámetros de entrada
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigo",dto.getCodigo());
            parametros.put("annos", dto.getAnnos() != null ? dto.getAnnos() : "");
            parametros.put("tipos", dto.getTipos() != null ? dto.getTipos() : "");
            parametros.put("recibo", dto.getIdrecibo() != null ? dto.getIdrecibo() : "");
            parametros.put("criterio", dto.getCriterio() != null ? dto.getCriterio() : "");
            //InputStream logo = this.getClass().getResourceAsStream("/static/images/logo.png");
            parametros.put("REPORT_DIR", "https://web.munilavictoria.gob.pe/mlv/images/logo_visa.jpg");
            //llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream,parametros,dataSource);

            // 5. Exportar a PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Error al generar el reporte Jasper: " + e.getMessage(), e);
        }catch (IllegalArgumentException | IllegalStateException e) {
            throw e;
        }catch (Exception e) {
            // Otros errores generales
            throw new RuntimeException("Ocurrió un error inesperado al generar el reporte PDF: " + e.getMessage(), e);
        }
    }

}
