package pe.gob.mlvictoria.complejo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginRequest;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginResponse;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComResulLoginResponse;
import pe.gob.mlvictoria.complejo.repository.AdminConLoginRepository;
import pe.gob.mlvictoria.complejo.service.AdminConLoginService;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;

@Service
@RequiredArgsConstructor
public class AdminConLoginServiceImpl implements AdminConLoginService {

    private final AdminConLoginRepository adminConLoginRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    @Override
    public AdminComResulLoginResponse loginConplejoDeportivo(AdminComLoginRequest dto) {

        if(dto.getNumDocumento() == null || dto.getNumDocumento().isEmpty()){
            throw new BusinessException("El número de documento no puede ser nulo o vacío");
        }

        AdminComLoginResponse response = adminConLoginRepository.loginComplejoDeportivo(dto);

        if (response == null){
            throw new BusinessException("Usuario no encontrado");
        }

        if(response.getEstado() != 1 ){
            throw new BusinessException("El usuario está inactivo");
        }

        boolean passwordCorrecta = passwordEncoder.matches(dto.getPassword(), response.getPassword());

        if (!passwordCorrecta) {
            throw new BusinessException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(response.getNumDoc());

        AdminComResulLoginResponse tokenResponse = new AdminComResulLoginResponse();
        tokenResponse.setToken(token);
        tokenResponse.setNombres(response.getNombres());
        tokenResponse.setPaterno(response.getPaterno());
        tokenResponse.setMaterno(response.getMaterno());
        tokenResponse.setNumDoc(response.getNumDoc());

        return tokenResponse;
    }
}
