package pe.gob.mlvictoria.fiscalizacioncontrol.service;

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

import java.util.List;

public interface CalificaMultaService {
    List<CalificaMultaResponseDTO> listarCalificaMulta(CalificaMultaRequestDTO dto);
    List<ViasResponseDTO> listarVias(ViasRequestDTO dto);
    List<GirosResponseDTO> listarGiros(GirosRequestDTO dto);
    List<InspectoresResponseDTO> listarInspector(InspectoresRequestDTO dto);
    List<ListasRespondeDTO> listas(ListasRequestDTO dto);
    List<InfraccionResponseDTO> listasInfraccion(InfraccionRequestDTO dto);
    RegistrarNotificacionResponseDTO registrarNotificacion(RegistrarNotificacionRequestDTO dto);
    NumeroNotificacionResponseDTO numeroNotificacion(NumeroNotificacionRequestDTO dto);
    byte[] imprimirNotificacionCargo(ReporteNotificacionRequestDTO dto);
    List<ReporteNotificacionResponseDTO> imprimirCargoActa(ReporteNotificacionRequestDTO dto);
}
