package pe.gob.mlvictoria.pagolinea.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentReq;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentResponse;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.ReciboResultDTO;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.UpdateAfterAuthRe;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContiResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.*;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.repository.PagoLiniaRepository;
import pe.gob.mlvictoria.pagolinea.service.PagoLiniaService;
import pe.gob.mlvictoria.utility.Utility;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PagoLiniaServiceImpl  implements PagoLiniaService {

    @Autowired
    private PagoLiniaRepository repository;

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private Utility utility;

    @Override
    public List<ReciboResponseDTO> validarRecibo(ReciboRequestDTO requestDTO) {
        String idrecibo = utility.decodeRecibos(requestDTO.getJson());

        if(requestDTO.getCriterio().equals("44")){
            requestDTO.setAnnos("*2025*");
            requestDTO.setTipos("*11.00*,*02.01*");
            requestDTO.setCriterio("44");
            int mesActual = LocalDate.now().getMonthValue();
            if (mesActual <= 6) {
                requestDTO.setPerio("*01*,*02*,*03*,*04*,*05*,*06*");
            }else{
                requestDTO.setPerio("*07*,*08*,*09*,*10*,*11*,*12*");
            }
        }
        requestDTO.setAnnos(utility.safe(requestDTO.getAnnos()));
        requestDTO.setTipos(utility.safe(requestDTO.getTipos()));
        requestDTO.setTipoRec(utility.safe(requestDTO.getTipoRec()));
        requestDTO.setPerio(utility.safe(requestDTO.getPerio()));
        requestDTO.setPredio(utility.safe(requestDTO.getPredio()));
        requestDTO.setCriterio(utility.safe(requestDTO.getCriterio()));
        requestDTO.setUsuario(utility.safe(requestDTO.getUsuario()));

        requestDTO.setIdreciboGroup(idrecibo);
        return repository.validarRecibo(requestDTO);
    }

    @Override
    public DatosContiResponseDTO obtenerDatosContribuyente(DatosContriRequestDTO requestDTO) {
        try {
            if(requestDTO.getOpcion().trim().isEmpty() && requestDTO.getCodigo().trim().isEmpty()){
                throw new IllegalArgumentException("Opción y Código son requeridos");
            }
            return repository.obtenerDatosContribuyente(requestDTO);
        }catch (Exception e){
            throw new RuntimeException("Ocurrió un error inesperado al generar el reporte PDF: " + e.getMessage(), e);
        }
    }

    @Override
    public NumCompraResponseDTO obtenerNumeroCompra(NumCompraRequestDTO requestDTO) {
        try {
            if(requestDTO.getOpcion().trim().isEmpty()){
                throw new IllegalArgumentException("Opción es requeridos");
            }
            return repository.obtenerNumeroCompra(requestDTO);
        }catch (Exception e){
            throw new RuntimeException("Ocurrió un error inesperado al generar el reporte PDF: " + e.getMessage(), e);
        }
    }

    @Override
    public CreateIntentResponse crearIntento(CreateIntentReq req, HttpServletRequest http) {
        try {
            String currency = (req.getCurrency() == null || req.getCurrency().isBlank()) ? "PEN" : req.getCurrency().trim();
            BigDecimal amount   = req.getAmount();
            String pn       = req.getPurchaseNumber();
            String ip       = req.getIp();
            String userAgent= req.getUserAgent();
            String sessionKey = req.getSessionKey();
            String detaDataJson = req.getDetaDataJson();

            CreateIntentReq dto = new CreateIntentReq(
                    pn,
                    amount,
                    currency,
                    req.getCodigo(),
                    req.getOrdenanza(),
                    req.getUsuario(),
                    sessionKey,
                    ip,
                    userAgent,
                    detaDataJson,
                    req.getModuloAgente()
            );

            var res = repository.crearIntento(dto);
            return new CreateIntentResponse(res.reciboId(), pn, amount, currency);
        }catch (Exception e) {
            System.out.println("error al intenatr registro de recibo en base de datos");
            return new CreateIntentResponse(null, "0", null, "0");
        }
    }

    @Override
    public Integer actualizarDespuesDeAuth(UpdateAfterAuthRe requestDTO) {
        try {
            if (requestDTO == null
                    || requestDTO.getReciboId() == null
                    || requestDTO.getPurchase_number() == null
                    || requestDTO.getPurchase_number().isBlank()) {
                log.warn("actualizarDespuesDeAuth: request inválido {}", requestDTO);
                return 0;
            }
            return repository.actualizarDespuesDeAuth(requestDTO);
        }catch (Exception e){
            log.error("Error actualizando recibo post-auth", e);
            return 0;
        }
    }

    @Override
    public ReciboResultDTO obtenerResultadoVisa(Long reciboId, String purchaseNumber) {
        try {
            if (reciboId == null || purchaseNumber == null || purchaseNumber.isBlank()) {
                return null;
            }
            return repository.obtenerResultadoVisa(reciboId,purchaseNumber);
        }catch (Exception e){
            log.error("Error actualizando recibo post-auth", e);
            return null;
        }
    }

    @Override
    @Transactional
    public List<GenerarReciboResponseDTO> generarRecibo(GenerarReciboRequestDTO requestDto) {
        try {
            log.info("Iniciando generación de recibo para código: {}", requestDto.getCodigo());

            List<GenerarReciboResponseDTO> response = repository.generarRecibo(requestDto);

            if (response == null) {
                log.warn("No se generó recibo para código: {}", requestDto.getCodigo());
                throw new RuntimeException("No se pudo generar el recibo, respuesta vacía");
            }
            log.info("Recibo generado correctamente: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error al generar recibo para código {}: {}", requestDto.getCodigo(), e.getMessage(), e);
            throw new RuntimeException("Error en la generación del recibo", e);
        }
    }

    @Override
    public List<GenerarReciboResponseDTO> generarPasarela(TransaccionRequestDTO requestDTO) {
        try {
            List<GenerarReciboResponseDTO> response = repository.generarPasarela(requestDTO);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NumeroCompraResponseDTO consultarNumeroCompra(NumeroCompraRequestDTO requestDTO) {
       try {
           NumeroCompraResponseDTO response = repository.consultarNumeroCompra(requestDTO);
           return  response;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public Integer actualizarIntento(NumeroCompraRequestDTO requestDTO) {
        try {
            if (requestDTO == null
                    || requestDTO.getPurchaseNumber() == null
                    || requestDTO.getPurchaseNumber().isBlank()) {
                log.warn("Numero de compra es requerido {}", requestDTO);
                return 0;
            }
            return repository.actualizarIntento(requestDTO);
        }catch (Exception e){
            log.error("Error actualizando recibo post-auth", e);
            return 0;
        }
    }

}
