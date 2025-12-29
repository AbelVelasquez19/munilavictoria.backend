package pe.gob.mlvictoria.complejo.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.reserva.*;
import pe.gob.mlvictoria.complejo.repository.ReservaRepository;
import pe.gob.mlvictoria.complejo.service.ReservaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    final ReservaRepository reservaRepository;

    @Override
    public ReservaRegistrarResponse registrarReserva(ReservaRegistrarRequest dto,HttpServletRequest req) {
        if(dto.getIdCancha() == null){
            throw new BusinessException("El id cancha no puede ser nulo");
        }
        if(dto.getFecha() == null){
            throw new BusinessException("Fecha no puede ser nulo");
        }
        if(dto.getIdAdministrado() == null){
            throw new BusinessException("El id del administrado no puede ser nulo");
        }
        dto.setOperador("WEB_COMPLEJO");
        dto.setEstacion(extractClientIp(req));
        ReservaRegistrarResponse response =  reservaRepository.registrarReserva(dto);
        if(response.getStatus() == 0){
            throw new BusinessException(response.getMessage());
        }
        if(response.getIdReserva() == 0){
            throw new BusinessException(response.getMessage());
        } else {
            return response;
        }
    }

    @Override
    public ReservaCancelarResponse cancelarReserva(ReservaCancelarRequest dto) {
        if(dto.getIdReserva() == null){
            throw new BusinessException("El id cancha no puede ser nulo");
        }
        ReservaCancelarResponse response =  reservaRepository.cancelarReserva(dto);
        if(response.getStatus() ==0){
            throw new BusinessException(response.getMessage());
        } else {
            return response;
        }
    }

    @Override
    public ReservaDetalleResponse reservaDetalle(ReservaDetalleRequest dto) {
        if(dto.getIdReserva() == null){
            throw new BusinessException("El id reserva no puede ser nulo");
        }
        ReservaDetalleResponse response =  reservaRepository.reservaDetalle(dto);
        if(response == null){
            throw new BusinessException("No se encontró la reserva");
        }
        if(response.getStatus() == 0){
            throw new BusinessException(response.getMessage());
        }
        else {
            return response;
        }
    }

    @Override
    public ReservaHrsLibResponse liberarReservaExpirada() {
        ReservaHrsLibResponse response = reservaRepository.liberarReservaExpirada();
        if(response.getStatus() != 1){
            throw new BusinessException(response.getMessage());
        } else {
            return response;
        }
    }

    @Override
    public int pagarReserva(int idReserva, String idRecibo, String tokenId) {
        int result = reservaRepository.pagarReserva(idReserva,idRecibo, tokenId);
        return result;
    }

    @Override
    public int actualizarReciboNiubiz(ActuReciNiubizRequest dto) {
        int rowsAffected = reservaRepository.actualizarReciboNiubiz(dto);
        return rowsAffected;
    }

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "CF-Connecting-IP",
                "True-Client-IP",
                "Forwarded"
        };

        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }

}
