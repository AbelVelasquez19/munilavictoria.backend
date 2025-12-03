package pe.gob.mlvictoria.fiscalizacioncontrol.service;

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

import java.util.List;

public interface CalificaContriService {
    List<CalificaContriResponseDTO> listaCalificaContribuyente (CalificaContriRequestDTO dto);
    List<DistritoResponse> listarDistrito(String query);
    List<TipoUrbaniazacionResponse> listarTipoUrbanizacion(String query);
    List<TipoViaResponse> listarTipoVia(String query);
    List<ViasContriResponse> listarViasContri(ViasContriRequest dto);
    List<ViasContriTotalResponse> listarViasContriTotal(ViasContriRequest dto);
    ContribuyenteResponse registrarContribuyente(ContribuyenteRequest dto);
}
