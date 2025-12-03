package pe.gob.mlvictoria.accesosigmun.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.accesosigmun.dto.ApiResponse;
import pe.gob.mlvictoria.accesosigmun.dto.LoginResponseDTO;
import pe.gob.mlvictoria.accesosigmun.dto.LogoutRequestDTO;
import pe.gob.mlvictoria.accesosigmun.model.Logout;
import pe.gob.mlvictoria.accesosigmun.services.LoginService;

@RestController
@RequestMapping("/api/sigmun")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> logout(@RequestBody LogoutRequestDTO dto, HttpServletRequest req){
        return loginService.logout(dto,req);
    }

}
