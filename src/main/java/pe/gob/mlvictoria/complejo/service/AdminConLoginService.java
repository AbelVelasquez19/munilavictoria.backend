package pe.gob.mlvictoria.complejo.service;

import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginRequest;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginResponse;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComResulLoginResponse;

public interface AdminConLoginService {
    AdminComResulLoginResponse loginConplejoDeportivo(AdminComLoginRequest dto);
}
