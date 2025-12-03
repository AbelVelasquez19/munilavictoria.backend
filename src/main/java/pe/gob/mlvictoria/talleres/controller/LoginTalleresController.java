package pe.gob.mlvictoria.talleres.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mlvictoria.talleres.dto.ApiResponse;
import pe.gob.mlvictoria.talleres.dto.login.LoginRequest;
import pe.gob.mlvictoria.talleres.dto.login.LoginTokenResponse;
import pe.gob.mlvictoria.talleres.service.LoginTalleresService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@Slf4j
public class LoginTalleresController {

    private final LoginTalleresService loginService;
    @PostMapping("/login-talleres")
    public ResponseEntity<ApiResponse<LoginTokenResponse>> loginTalleres(@RequestBody LoginRequest loginRequest) {
        LoginTokenResponse response = loginService.loginTalleres(loginRequest);
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
