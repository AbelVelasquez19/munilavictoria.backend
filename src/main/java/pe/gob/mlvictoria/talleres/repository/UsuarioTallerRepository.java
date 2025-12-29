package pe.gob.mlvictoria.talleres.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.mlvictoria.talleres.dto.usuario.*;
import pe.gob.mlvictoria.talleres.mapper.UsuarioTallerMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class UsuarioTallerRepository {
    @Autowired
    final UsuarioTallerMapper usuarioTallerMapper;

    public List<UsuarioTallerResponse> listarUsuarioTaller(UsuarioTallerRequest dto) {
        return usuarioTallerMapper.listarUsuarioTaller(dto);
    }

    public RegisUsuarioTallerResponse registrarUsuarioTaller(RegisUsuarioTallerRequest dto) {
        return usuarioTallerMapper.registrarUsuarioTaller(dto);
    }

    public ObtUsuarioTallerResponse obtenerUsuarioTaller(ObtUsuarioTallerRequest dto) {
        return usuarioTallerMapper.obtenerUsuarioTaller(dto);
    }

    public ActUsuarioTallerResponse actualizarUsuarioTaller(ActUsuarioTallerRequest dto) {
        return usuarioTallerMapper.actualizarUsuarioTaller(dto);
    }
}
