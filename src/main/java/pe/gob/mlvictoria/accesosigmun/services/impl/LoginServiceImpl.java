package pe.gob.mlvictoria.accesosigmun.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.accesosigmun.dto.ApiResponse;
import pe.gob.mlvictoria.accesosigmun.dto.LoginResponseDTO;
import pe.gob.mlvictoria.accesosigmun.dto.LogoutRequestDTO;
import pe.gob.mlvictoria.accesosigmun.dto.RegistrarAccesoRequestDTO;
import pe.gob.mlvictoria.accesosigmun.model.Logout;
import pe.gob.mlvictoria.accesosigmun.repository.LoginRepository;
import pe.gob.mlvictoria.accesosigmun.repository.RegistrarAccesoRepository;
import pe.gob.mlvictoria.accesosigmun.services.LoginService;
import pe.gob.mlvictoria.config.JwtUtil;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RegistrarAccesoRepository registrarAccesoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<ApiResponse<LoginResponseDTO>> logout(LogoutRequestDTO dto, HttpServletRequest req) {
        try {
            Logout res = loginRepository.logout(dto);
            if(res==null){
                return ResponseEntity.status(404).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "Nombre de usuario y/o clave incorrectos.",
                                null
                        )
                );
            }
            if(res.getNestado()==1){
                RegistrarAccesoRequestDTO raDto = new RegistrarAccesoRequestDTO();
                raDto.setOpcion(1);
                raDto.setIdUsuario(res.getIdUsuario());
                raDto.setParametro(res.getVlogin());
                raDto.setEstacion(extractClientIp(req));
                raDto.setIdsistema(2);
                registrarAccesoRepository.RegistrarAcceso(raDto);
                log.info("Acceso registrado para usuario {}", res.getVlogin());
            }else{
                return ResponseEntity.status(404).body(
                        new ApiResponse<>(
                                LocalDateTime.now(),
                                "fail",
                                "Usuario Inhabilitado.",
                                null
                        )
                );
            }
            String token = jwtUtil.generateToken(res.getIdUsuario());
            res.setToken(token);
            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setIdUsuario( res.getIdUsuario());
            loginResponse.setToken(token);
            loginResponse.setNombre(res.getNombre());
            loginResponse.setUsuario(res.getVlogin());
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "success",
                            "Consulta de estado de cuenta exitosa",
                            loginResponse
                    )
            );

        }catch (Exception ex){
            log.error("Error en logout: {}", ex.getMessage(), ex);
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(
                            LocalDateTime.now(),
                            "fail",
                            "Error inesperado en la operación de logout.",
                            null
                    )
            );
        }
    }

    private String extractClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For","X-Real-IP","CF-Connecting-IP","True-Client-IP","Forwarded"};
        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
