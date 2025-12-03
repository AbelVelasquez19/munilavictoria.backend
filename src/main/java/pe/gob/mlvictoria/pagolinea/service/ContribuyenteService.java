package pe.gob.mlvictoria.pagolinea.service;

import pe.gob.mlvictoria.pagolinea.dto.*;

public interface ContribuyenteService {
    RecuperarContrasenaResponseDTO recuperarContrasena(ContribuyenteDTO dto);
    EnviarCodigoRecuperacionResponseDTO enviarCodigoRecuperacion(ContribuyenteDTO dto);
    EnviarCodigoRecuperacionResponseDTO enviaNuevaContrasena(ContribuyenteDTO dto);
    LoginResponseDTO login(LoginRequestDTO loginRequest);
    AnioPenContriRespondeDTO anioPendientePagar(AnioPenContriRespondeDTO dto);
}
