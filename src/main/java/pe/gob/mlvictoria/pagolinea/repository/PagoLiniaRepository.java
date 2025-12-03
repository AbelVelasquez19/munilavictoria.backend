package pe.gob.mlvictoria.pagolinea.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentReq;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentRes;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.ReciboResultDTO;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.UpdateAfterAuthRe;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContiResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.*;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboResponseDTO;
import pe.gob.mlvictoria.pagolinea.mapper.PagoLiniaMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PagoLiniaRepository {
    @Autowired
    private final PagoLiniaMapper mapper;

    public List<ReciboResponseDTO> validarRecibo (ReciboRequestDTO requestDTO){
        return mapper.validarRecibo(requestDTO);
    }

    public DatosContiResponseDTO obtenerDatosContribuyente (DatosContriRequestDTO requestDTO){
        return mapper.obtenerDatosContribuyente(requestDTO);
    }

    public NumCompraResponseDTO obtenerNumeroCompra (NumCompraRequestDTO requestDTO){
        return mapper.obtenerNumeroCompra(requestDTO);
    }

    public CreateIntentRes crearIntento(CreateIntentReq dto){
        return mapper.crearIntento(dto);
    }

    public Integer actualizarDespuesDeAuth(UpdateAfterAuthRe dto){
        return  mapper.actualizarDespuesDeAuth(dto);
    }

    public ReciboResultDTO obtenerResultadoVisa (Long reciboId,  String purchaseNumber){
        return  mapper.obtenerResultadoVisa(reciboId, purchaseNumber);
    }

    public List<GenerarReciboResponseDTO> generarRecibo (GenerarReciboRequestDTO dto){
        return  mapper.generarRecibo(dto);
    }

    public List<GenerarReciboResponseDTO> generarPasarela(TransaccionRequestDTO dto){
        return mapper.generarPasarela(dto);
    }

    public NumeroCompraResponseDTO consultarNumeroCompra(NumeroCompraRequestDTO dto){
        return mapper.consultarNumeroCompra(dto);
    }

    public Integer actualizarIntento(NumeroCompraRequestDTO dto){
        return  mapper.actualizarIntento(dto);
    }
}
