package pe.gob.mlvictoria.pagolinea.service;

import jakarta.servlet.http.HttpServletRequest;
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

import java.util.List;

public interface PagoLiniaService {
    List<ReciboResponseDTO> validarRecibo(ReciboRequestDTO requestDTO);
    DatosContiResponseDTO obtenerDatosContribuyente (DatosContriRequestDTO requestDTO);
    NumCompraResponseDTO obtenerNumeroCompra (NumCompraRequestDTO requestDTO);
    CreateIntentResponse crearIntento(CreateIntentReq req, HttpServletRequest http);
    Integer actualizarDespuesDeAuth(UpdateAfterAuthRe requestDTO);
    ReciboResultDTO obtenerResultadoVisa (Long reciboId,  String purchaseNumber);
    List<GenerarReciboResponseDTO> generarRecibo(GenerarReciboRequestDTO requestDto);
    List<GenerarReciboResponseDTO> generarPasarela (TransaccionRequestDTO requestDTO);
    NumeroCompraResponseDTO consultarNumeroCompra(NumeroCompraRequestDTO requestDTO);
    Integer actualizarIntento(NumeroCompraRequestDTO requestDTO);
}
