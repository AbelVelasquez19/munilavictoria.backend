package pe.gob.mlvictoria.talleres.service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.*;

public interface ApoderadoService {
    ApoderadoResponse registrarApoderado(GeneralRequest dto,HttpServletRequest request);
    String extractClientIp(HttpServletRequest request);
}
