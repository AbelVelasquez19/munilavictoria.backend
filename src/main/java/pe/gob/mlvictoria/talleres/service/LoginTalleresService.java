package pe.gob.mlvictoria.talleres.service;

import pe.gob.mlvictoria.talleres.dto.login.LoginRequest;
import pe.gob.mlvictoria.talleres.dto.login.LoginTokenResponse;

public interface LoginTalleresService {
    LoginTokenResponse loginTalleres(LoginRequest dto);
}
