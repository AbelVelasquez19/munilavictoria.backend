package pe.gob.mlvictoria.pagolinea.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.pagolinea.dto.AnioPenContriRespondeDTO;
import pe.gob.mlvictoria.pagolinea.dto.ContribuyenteDTO;
import pe.gob.mlvictoria.pagolinea.dto.EnviarCodigoRecuperacionResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.RecuperarContrasenaResponseDTO;
import pe.gob.mlvictoria.pagolinea.mapper.ContribuyenteMapper;
import pe.gob.mlvictoria.pagolinea.model.ContribuyenteLogin;

@Repository
@RequiredArgsConstructor
public class ContribuyenteRepository {

    @Autowired
    private final ContribuyenteMapper mapper;

    public RecuperarContrasenaResponseDTO recuperarContrasena(ContribuyenteDTO dto) {
        return mapper.recuperarContrasena(dto);
    }

    public EnviarCodigoRecuperacionResponseDTO enviarCodigoRecuperacion(ContribuyenteDTO dto){
        return mapper.enviarCodigoRecuperacion(dto);
    }

    public EnviarCodigoRecuperacionResponseDTO actualizarRecuperarContrasena(ContribuyenteDTO dto){
        return mapper.actualizarRecuperarContrasena(dto);
    }

    public EnviarCodigoRecuperacionResponseDTO enviaNuevaContrasena(ContribuyenteDTO dto){
        return mapper.enviaNuevaContrasena(dto);
    }

    public ContribuyenteLogin login(String codigo, String password) {
        return mapper.login(codigo, password);
    }

    public AnioPenContriRespondeDTO anioPendientePagar (AnioPenContriRespondeDTO dto){
        return  mapper.annioPendientePagar(dto);
    }
}
