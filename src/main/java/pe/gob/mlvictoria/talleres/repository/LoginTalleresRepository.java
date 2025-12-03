package pe.gob.mlvictoria.talleres.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.talleres.dto.login.LoginRequest;
import pe.gob.mlvictoria.talleres.dto.login.LoginResponse;
import pe.gob.mlvictoria.talleres.mapper.LoginTalleresMapper;

@Repository
@AllArgsConstructor
public class LoginTalleresRepository {
    @Autowired
    final LoginTalleresMapper loginMapper;

    public LoginResponse loginTalleres(LoginRequest dto) {
        return loginMapper.loginTalleres(dto);
    }
}
