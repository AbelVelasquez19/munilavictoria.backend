package pe.gob.mlvictoria.complejo.controlador;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComLoginRequest;
import pe.gob.mlvictoria.complejo.dto.adminlogin.AdminComResulLoginResponse;
import pe.gob.mlvictoria.complejo.service.AdminConLoginService;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/complejo-admin")
@RequiredArgsConstructor
@Slf4j
public class AdminConLoginController {

    private final AdminConLoginService loginService;

    @PostMapping("/login-complejo")
    public ResponseEntity<ApiResponse<AdminComResulLoginResponse>> loginTalleres(@RequestBody AdminComLoginRequest loginRequest) {
        AdminComResulLoginResponse response = loginService.loginConplejoDeportivo(loginRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        LocalDateTime.now(),
                        "success",
                        "Login exitoso",
                        response
                )
        );
    }
}
