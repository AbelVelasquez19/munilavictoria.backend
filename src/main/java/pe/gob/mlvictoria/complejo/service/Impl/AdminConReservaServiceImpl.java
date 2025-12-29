package pe.gob.mlvictoria.complejo.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.adminreserva.*;
import pe.gob.mlvictoria.complejo.repository.AdminConReservaRepository;
import pe.gob.mlvictoria.complejo.service.AdminConReservaService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.utility.Utility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminConReservaServiceImpl implements AdminConReservaService {

    private final AdminConReservaRepository adminConReservaRepository;

    @Autowired
    private Utility utility;

    @Override
    public List<AdminConReservaResponse> listarReservasDeportivo(AdminConReservaRequest dto) {
        if(dto.getFecha() == null || dto.getIdComplejo() == null || dto.getIdCancha() == null){
            throw new BusinessException("Los campos fecha, idComplejo e idCancha son obligatorios.");
        }
        return adminConReservaRepository.listarReservasDeportivo(dto);
    }

    @Override
    public HorarioMasivaResponsive generarHorarioMasiva(HorarioMasivaRequest dto, HttpServletRequest req) {
        dto.setEstacion(utility.extractClientIp(req));

        if(dto.getIdComplejo() == null || dto.getListaCanchas() == null || dto.getListaFechas() == null
                || dto.getOperador() == null || dto.getEstacion() == null){
            throw new BusinessException("Todos los campos son obligatorios.");
        }

        return adminConReservaRepository.generarHorarioMasiva(dto);
    }

    @Override
    public CambiarEstadoHorarioResponse cambiarEstadoHorario(CambiarEstadoHorarioRequest dto,HttpServletRequest req) {
        dto.setEstacion(utility.extractClientIp(req));
        if(dto.getIdEstadoCancha() == null || dto.getNuevoEstado() == null){
            throw new BusinessException("Los campos idHorario y nuevoEstado son obligatorios.");
        }
        return adminConReservaRepository.cambiarEstadoHorario(dto);
    }

}
