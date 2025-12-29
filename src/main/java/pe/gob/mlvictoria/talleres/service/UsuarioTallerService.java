package pe.gob.mlvictoria.talleres.service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.mlvictoria.talleres.dto.usuario.*;

import java.util.List;

public interface UsuarioTallerService {
    List<UsuarioTallerResponse> listarUsuarioTaller(UsuarioTallerRequest dto);
    RegisUsuarioTallerResponse registrarUsuarioTaller(UsuarioTallerGeneralRequest dto, HttpServletRequest req);
    ObtUsuarioTallerResponse obtenerUsuarioTaller(ObtUsuarioTallerRequest dto);
    ActUsuarioTallerResponse actualizarUsuarioTaller(ActUsuarioTallerRequest dto);
}
