package pe.gob.mlvictoria.accesosigmun.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import pe.gob.mlvictoria.accesosigmun.dto.ApiResponse;
import pe.gob.mlvictoria.accesosigmun.dto.LoginResponseDTO;
import pe.gob.mlvictoria.accesosigmun.dto.LogoutRequestDTO;
import pe.gob.mlvictoria.accesosigmun.model.Logout;

public interface LoginService {
    ResponseEntity<ApiResponse<LoginResponseDTO>> logout(LogoutRequestDTO dto, HttpServletRequest req);
}
