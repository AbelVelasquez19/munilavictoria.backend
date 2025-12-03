package pe.gob.mlvictoria.complejo.service;

import jakarta.servlet.http.HttpServletRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminGeneRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminResponse;

public interface ComAdminService {
    ComAdminResponse registrarComAdmin(ComAdminGeneRequest dto, HttpServletRequest req);
}
