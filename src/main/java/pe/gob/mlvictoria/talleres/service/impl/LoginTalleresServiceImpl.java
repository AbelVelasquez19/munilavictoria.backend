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
        if(dto.getPassword() == null || dto.getPassword().isEmpty()){
            throw new BusinessException("La contraseña no puede ser nula o vacía");
        }

        LoginResponse response = loginRepository.loginTalleres(dto);

        if (response.getStatus() == 0) {
            throw new BusinessException(response.getMessage());
        }

        boolean passwordCorrecta = passwordEncoder.matches(dto.getPassword(), response.getPasswordHash());

        if (!passwordCorrecta) {
            throw new BusinessException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(response.getDni());
        LoginTokenResponse tokenResponse = new LoginTokenResponse();
        tokenResponse.setToken(token);
        tokenResponse.setNombreCompleto(response.getNombreCompleto());
        tokenResponse.setNumDoc(response.getDni());
        tokenResponse.setIdUsuario(response.getIdUsuario());
        tokenResponse.setIdRol(response.getIdRol());
        tokenResponse.setNombreRol(response.getRolNombre());
        return tokenResponse;
    }
}
