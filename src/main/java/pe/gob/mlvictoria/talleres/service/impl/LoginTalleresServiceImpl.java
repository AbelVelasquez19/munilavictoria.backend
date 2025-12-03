package pe.gob.mlvictoria.talleres.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.talleres.dto.login.LoginRequest;
import pe.gob.mlvictoria.talleres.dto.login.LoginResponse;
import pe.gob.mlvictoria.talleres.dto.login.LoginTokenResponse;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.talleres.repository.LoginTalleresRepository;
import pe.gob.mlvictoria.talleres.service.LoginTalleresService;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginTalleresServiceImpl implements LoginTalleresService {
    private final LoginTalleresRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    @Override
    public LoginTokenResponse loginTalleres(LoginRequest dto) {
        if(dto.getNumDocumento() == null || dto.getNumDocumento().isEmpty()){
            throw new BusinessException("El número de documento no puede ser nulo o vacío");
        }

        LoginResponse response = loginRepository.loginTalleres(dto);

        if (response == null){
            throw new BusinessException("Usuario no encontrado");
        }

        if(response.getNestado() != 1){
            throw new BusinessException("El usuario está inactivo");
        }

        boolean passwordCorrecta = passwordEncoder.matches(dto.getPassword(), response.getPassword());

        if (!passwordCorrecta) {
            throw new BusinessException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(response.getNumDoc());

        LoginTokenResponse tokenResponse = new LoginTokenResponse();
        tokenResponse.setToken(token);
        tokenResponse.setNombres(response.getNombres());
        tokenResponse.setPaterno(response.getPaterno());
        tokenResponse.setMaterno(response.getMaterno());
        tokenResponse.setNumDoc(response.getNumDoc());

        return tokenResponse;
    }
}
