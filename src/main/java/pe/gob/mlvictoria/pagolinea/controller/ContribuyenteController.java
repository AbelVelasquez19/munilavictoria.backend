package pe.gob.mlvictoria.pagolinea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mlvictoria.config.JwtUtil;
import pe.gob.mlvictoria.pagolinea.dto.*;
import pe.gob.mlvictoria.pagolinea.service.ContribuyenteService;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/contribuyente")
public class ContribuyenteController {

    @Autowired
    private ContribuyenteService contribuyenteService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<RecuperarContrasenaResponseDTO> recuperarContrasena(@RequestBody ContribuyenteDTO dto) {
        RecuperarContrasenaResponseDTO response = contribuyenteService.recuperarContrasena(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/enviar-codigo-recuperacion")
    public ResponseEntity<EnviarCodigoRecuperacionResponseDTO> enviarCodigoRecuperacion(@RequestBody ContribuyenteDTO dto){
        EnviarCodigoRecuperacionResponseDTO responde = contribuyenteService.enviarCodigoRecuperacion(dto);
        return  ResponseEntity.ok(responde);
    }

    @PostMapping("/enviar-nueva-contrasena")
    public ResponseEntity<EnviarCodigoRecuperacionResponseDTO> enviaNuevaContrasena(@RequestBody ContribuyenteDTO dto){
        EnviarCodigoRecuperacionResponseDTO responde = contribuyenteService.enviaNuevaContrasena(dto);
        return  ResponseEntity.ok(responde);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = contribuyenteService.login(loginRequest);
        if ("200".equals(response.getStatus())) {
            String token = jwtUtil.generateToken(response.getCodigo());
            response.setToken(token);
            return ResponseEntity.ok(new ApiResponse<>(LocalDateTime.now(),"success", "Login exitoso", response));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse<>(LocalDateTime.now(),"fail", "Credenciales inválidas", null));
        }
    }

    @PostMapping("/anio-pendiente-pagar")
    public ResponseEntity<ApiResponse<AnioPenContriRespondeDTO>> anioPendietePagar(@RequestHeader("Authorization") String authHeader,@RequestBody AnioPenContriRespondeDTO dto){
        String token = authHeader.replace("Bearer ", "");
        String codigoContribuyente = jwtUtil.extractUsername(token);
        dto.setCodigo(codigoContribuyente);

        AnioPenContriRespondeDTO responde = contribuyenteService.anioPendientePagar(dto);
        if (responde!=null && responde.getCodigo()!=null){
            return  ResponseEntity.ok(new ApiResponse<>(LocalDateTime.now(),"success", "consulta exitosa", responde));
        }
       return ResponseEntity.status(401).body(new ApiResponse<>(LocalDateTime.now(),"fail","Error al consultar",null));
    }
}
