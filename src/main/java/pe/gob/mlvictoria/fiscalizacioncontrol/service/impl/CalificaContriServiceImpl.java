package pe.gob.mlvictoria.fiscalizacioncontrol.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import pe.gob.mlvictoria.fiscalizacioncontrol.repository.CalificaContriRepository;
import pe.gob.mlvictoria.fiscalizacioncontrol.service.CalificaContriService;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CalificaContriServiceImpl implements CalificaContriService {

    @Autowired
    private CalificaContriRepository calificaContriRepository;

    @Override
    public List<CalificaContriResponseDTO> listaCalificaContribuyente(CalificaContriRequestDTO dto) {
       try {
         return calificaContriRepository.listaCalificaContribuyente(dto);
       } catch (Exception e) {
           log.error("Error al listar contribuyentes: {}", e.getMessage(), e);
           return Collections.emptyList();
       }
    }

    @Override
    public List<DistritoResponse> listarDistrito(String query){
        try {
            return calificaContriRepository.listarDistrito(query);
        } catch (Exception e) {
            log.error("Error al listar distritos: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<TipoUrbaniazacionResponse> listarTipoUrbanizacion(String query) {
        try {
            return calificaContriRepository.listarTipoUrbanizacion(query);
        } catch (Exception e) {
            log.error("Error al listar distritos: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<TipoViaResponse> listarTipoVia(String query) {
        try {
            return calificaContriRepository.listarTipoVia(query);
        } catch (Exception e) {
            log.error("Error al listar distritos: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ViasContriResponse> listarViasContri(ViasContriRequest dto) {
        try {
            return calificaContriRepository.listarViasContri(dto);
        } catch (Exception e) {
            log.error("Error al listar vias contribuyente: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ViasContriTotalResponse> listarViasContriTotal(ViasContriRequest dto) {
        try {
            return calificaContriRepository.listarViasContriTotal(dto);
        } catch (Exception e) {
            log.error("Error al listar vias contribuyente: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public ContribuyenteResponse registrarContribuyente(ContribuyenteRequest dto) {
        try {
            return calificaContriRepository.RegistrarContribuyente(dto);
        } catch (Exception e) {
            log.error("Error al listar vias contribuyente: {}", e.getMessage(), e);
            return null;
        }
    }

}
