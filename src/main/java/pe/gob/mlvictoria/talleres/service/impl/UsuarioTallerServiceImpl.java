package pe.gob.mlvictoria.talleres.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminGeneRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteResponse;
import pe.gob.mlvictoria.talleres.dto.usuario.*;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.talleres.repository.ApoderadoRepository;
import pe.gob.mlvictoria.talleres.repository.UsuarioTallerRepository;
import pe.gob.mlvictoria.talleres.service.UsuarioTallerService;
import pe.gob.mlvictoria.utility.Utility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioTallerServiceImpl implements UsuarioTallerService {
    private final UsuarioTallerRepository usuarioTallerRepository;
    private final Utility utility;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ApoderadoRepository apoderadoRepository;

    @Override
    public List<UsuarioTallerResponse> listarUsuarioTaller(UsuarioTallerRequest dto) {
         return usuarioTallerRepository.listarUsuarioTaller(dto);
    }

    @Override
    public RegisUsuarioTallerResponse registrarUsuarioTaller(UsuarioTallerGeneralRequest dto,HttpServletRequest req) {
        ContribuyenteRequest contrib = mapContribuyente(dto, req);
        ContribuyenteResponse respContri = apoderadoRepository.registrarContribuyente(contrib);

        if (respContri == null || respContri.getCodigo() == null) {
            throw new BusinessException("No se pudo registrar el contribuyente.");
        }

        RegisUsuarioTallerRequest usuTaller = mapUsuarioTaller(dto, respContri.getCodigo(), req);
        RegisUsuarioTallerResponse respUsuTaller = usuarioTallerRepository.registrarUsuarioTaller(usuTaller);
        if (respUsuTaller == null) {
            throw new BusinessException("No se pudo registrar el usuario.");
        }

        return respUsuTaller;
    }

    @Override
    public ObtUsuarioTallerResponse obtenerUsuarioTaller(ObtUsuarioTallerRequest dto) {
        if(dto.getIdUsuario()==null || dto.getIdUsuario()==0){
            throw new BusinessException("El idUsuario es obligatorio.");
        }
        return usuarioTallerRepository.obtenerUsuarioTaller(dto);
    }

    @Override
    public ActUsuarioTallerResponse actualizarUsuarioTaller(ActUsuarioTallerRequest dto) {
        if(dto.getIdUsuario()==null || dto.getIdUsuario()==0){
            throw new BusinessException("El idUsuario es obligatorio.");
        }
        if(dto.getIdRol()==null || dto.getIdRol()==0){
            throw new BusinessException("El idRol es obligatorio.");
        }
        if (dto.getCodigo()==null || dto.getCodigo().isEmpty()) {
            throw new BusinessException("El codigo es obligatorio.");
        }
        return usuarioTallerRepository.actualizarUsuarioTaller(dto);
    }

    private RegisUsuarioTallerRequest mapUsuarioTaller(UsuarioTallerGeneralRequest dto, String codigoContribuyente, HttpServletRequest req) {
        RegisUsuarioTallerRequest  c = new RegisUsuarioTallerRequest();
        c.setCodigo(codigoContribuyente);
        c.setUsername(dto.getNumeroDocumento());
        c.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        c.setIdRol(dto.getIdRol());
        c.setOperador("WEB_TALLERES");
        c.setEstacion(utility.extractClientIp(req));
        return c;
    }

    private ContribuyenteRequest mapContribuyente(UsuarioTallerGeneralRequest dto, HttpServletRequest req) {
        ContribuyenteRequest c = new ContribuyenteRequest();
        c.setIdTipoContri("01");
        c.setNombres(dto.getNombre());
        c.setPaterno(dto.getPaterno());
        c.setMaterno(dto.getMaterno());
        c.setSexo("M");
        c.setCorreoE(dto.getCorreo());
        c.setIdDoc(dto.getTipoDocumento());
        c.setNumDoc(dto.getNumeroDocumento());
        c.setDireccion("");
        c.setTelefono(dto.getTelefono());
        c.setValida(0);
        c.setOperador("WEB_TALLERES");
        c.setEstacion(utility.extractClientIp(req));
        return c;
    }

}
