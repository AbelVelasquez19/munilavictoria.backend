package pe.gob.mlvictoria.fiscalizacioncontrol.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.mapper.*;

import java.util.List;

@Repository
@AllArgsConstructor
public class CalificaMultaRepository {

    private final CalificaMultaMapper calificaMultaMapper;
    private final ViasMapper viasMapper;
    private final GirosMapper girosMapper;
    private final InspectorMapper inspectorMapper;
    private final ListasMapper listasMapper;
    private final InfraccionMapper infraccionMapper;

    public List<CalificaMultaResponseDTO> listarCalificaMulta(CalificaMultaRequestDTO dto){
        return calificaMultaMapper.listaCalificaMulta(dto);
    }

    public List<ViasResponseDTO> listarVias(ViasRequestDTO dto){
        return viasMapper.listarVias(dto);
    }

    public List<GirosResponseDTO> listarGiros(GirosRequestDTO dto){
        return girosMapper.listarGiros(dto);
    }

    public List<InspectoresResponseDTO> listarInspector(InspectoresRequestDTO dto){
        return inspectorMapper.listarInspectores(dto);
    }

    public List<ListasRespondeDTO> listas(ListasRequestDTO dto){
        return listasMapper.listas(dto);
    }

    public List<InfraccionResponseDTO> listaInfraccion(InfraccionRequestDTO dto){
        return infraccionMapper.listarInfraccion(dto);
    }

    public NumeroNotificacionResponseDTO numeroNotificacion(NumeroNotificacionRequestDTO dto){
        return listasMapper.NumeroNotificacion(dto);
    }

    public List<ReporteNotificacionResponseDTO> imprimirCargoActa(ReporteNotificacionRequestDTO dto){
        return listasMapper.imprimirCargoActa(dto);
    }
}
