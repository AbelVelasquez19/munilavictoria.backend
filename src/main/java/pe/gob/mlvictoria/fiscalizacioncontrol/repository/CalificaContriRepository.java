package pe.gob.mlvictoria.fiscalizacioncontrol.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.distrito.DistritoResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriRequestDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.listado.CalificaContriResponseDTO;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarcontribuyente.ContribuyenteRequest;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.registrarcontribuyente.ContribuyenteResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipourbanizacion.TipoUrbaniazacionResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.tipovia.TipoViaResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriRequest;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.dto.vias.ViasContriTotalResponse;
import pe.gob.mlvictoria.fiscalizacioncontrol.mapper.*;

import java.util.List;

@Repository
@AllArgsConstructor
public class CalificaContriRepository {

    @Autowired
    final CalificaContriMapper calificaContriMapper;

    @Autowired
    final DistritoMapper distritoMapper;

    @Autowired
    final TipoViaMapper tipoViaMapper;

    @Autowired
    final TipoUrbanizacionMapper tipoUrbanizacionMapper;

    @Autowired
    final ViasMapper viasMapper;

    @Autowired
    final RegContribuyenteMapper contribuyenteMapper;

    public List<CalificaContriResponseDTO> listaCalificaContribuyente (CalificaContriRequestDTO dto){
        return calificaContriMapper.listaCalificaContribuyente(dto);
    }

    public List<DistritoResponse> listarDistrito(String query){
        return distritoMapper.listarDistrito(query);
    }

    public List<TipoUrbaniazacionResponse> listarTipoUrbanizacion(String query){
        return tipoUrbanizacionMapper.listarTipoUrbanizacion(query);
    }

    public List<TipoViaResponse> listarTipoVia(String query){
        return tipoViaMapper.listarTipoVia(query);
    }

    public List<ViasContriResponse> listarViasContri(ViasContriRequest dto){
        return viasMapper.listarViasContri(dto);
    }

    public List<ViasContriTotalResponse> listarViasContriTotal(ViasContriRequest dto){
        return viasMapper.listarViasContriTotal(dto);
    }

    public ContribuyenteResponse RegistrarContribuyente(ContribuyenteRequest dto){
        return contribuyenteMapper.RegistrarContribuyente(dto);
    }
}
