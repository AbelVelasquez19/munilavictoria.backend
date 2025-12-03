package pe.gob.mlvictoria.pagolinea.service.Impl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.pagolinea.dto.historialpago.HistorialPagoResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.ImprimirReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.repository.HistorialPagoRepository;
import pe.gob.mlvictoria.pagolinea.service.HistorialPagoService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistorialPagoServiceImpl implements HistorialPagoService {

    @Autowired
    private HistorialPagoRepository repository;

    @Override
    public List<HistorialPagoResponseDTO> annosRecibosPagados(DatosContriRequestDTO dto) {
        try {
            List<HistorialPagoResponseDTO> result = repository.annosRecibosPagados(dto);
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public  byte[] imprimirRecibo(ImprimirReciboRequestDTO dto) {
       try {
           if(dto.getCodigo()==null || dto.getCodigo().trim().isEmpty()){
               throw  new IllegalArgumentException("El parámetro codigo es obligatorio");
           }
           List<ImprimirReciboResponseDTO> result = repository.imprimirRecibo(dto);
           if(result==null || result.isEmpty()){
               throw  new IllegalArgumentException("No hay datos disponibles para generar el reporte");
           }
           InputStream jasperStream = new ClassPathResource("reportes/pagolinia/recibo.jasper").getInputStream();
           JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
           Map<String, Object> parametros = new HashMap<>();
           parametros.put("buscar1",dto.getBuscar());
           parametros.put("codigo",dto.getCodigo());
           parametros.put("numIngr",dto.getNumIngr());
           parametros.put("LOGO","https://web.munilavictoria.gob.pe/mlv/images/logo_visa.jpg");
           parametros.put("MARCA_AGUA","https://web.munilavictoria.gob.pe/mlv/images/marca_de_agua_mlv.jpg");
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream,parametros,dataSource);
           return JasperExportManager.exportReportToPdf(jasperPrint);

       }catch (JRException e){
           throw new RuntimeException("Error al generar el reporte Jasper: " + e.getMessage(), e);
       }catch (IllegalArgumentException | IllegalStateException e){
           throw e;
       } catch (Exception e){
            throw new RuntimeException("Ocurrió un error inesperado al generar el reporte PDF: " + e.getMessage(), e);
        }
    }
}
