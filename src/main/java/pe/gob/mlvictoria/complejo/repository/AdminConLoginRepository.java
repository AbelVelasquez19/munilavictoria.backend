package pe.gob.mlvictoria.complejo.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginRequest;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginResponse;
import pe.gob.mlvictoria.complejo.mapper.AdminConLoginMapper;

@Repository
@AllArgsConstructor
public class AdminConLoginRepository {

    @Autowired
    final AdminConLoginMapper adminConLoginMapper;

    public AdminComLoginResponse loginComplejoDeportivo(AdminComLoginRequest dto) {
        return adminConLoginMapper.loginComplejoDeportivo(dto);
    }

}
