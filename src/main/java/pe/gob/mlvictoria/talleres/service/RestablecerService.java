package pe.gob.mlvictoria.talleres.service;

import pe.gob.mlvictoria.talleres.dto.restablecer.ActPassRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.BusUsuCorreoRequest;
import pe.gob.mlvictoria.talleres.dto.restablecer.RestablecerResponse;
import pe.gob.mlvictoria.talleres.dto.restablecer.TokenRequest;

public interface RestablecerService {
    RestablecerResponse buscarUsuarioCorreo(BusUsuCorreoRequest dto);
    RestablecerResponse validarToken(TokenRequest dto);
    RestablecerResponse actualizarPassword(ActPassRequest dto);
}
