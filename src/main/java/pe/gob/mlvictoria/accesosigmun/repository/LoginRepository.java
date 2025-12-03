package pe.gob.mlvictoria.accesosigmun.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.accesosigmun.dto.LogoutRequestDTO;
import pe.gob.mlvictoria.accesosigmun.mapper.sigmun.LoginMapper;
import pe.gob.mlvictoria.accesosigmun.model.Logout;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    @Autowired
    private final LoginMapper mapper;

    public Logout logout(LogoutRequestDTO dto){
        return mapper.logout(dto);
    }
}
