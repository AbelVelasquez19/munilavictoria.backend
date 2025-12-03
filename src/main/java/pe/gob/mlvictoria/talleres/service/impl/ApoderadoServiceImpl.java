package pe.gob.mlvictoria.talleres.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.talleres.dto.apoderado.*;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.talleres.repository.ApoderadoRepository;
import pe.gob.mlvictoria.talleres.service.ApoderadoService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApoderadoServiceImpl implements ApoderadoService {

    private final ApoderadoRepository apoderadoRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApoderadoResponse registrarApoderado(GeneralRequest dto,HttpServletRequest req) {

        ContribuyenteRequest contrib = mapContribuyente(dto, req);
        ContribuyenteResponse respContri = apoderadoRepository.registrarContribuyente(contrib);

        if (respContri == null || respContri.getCodigo() == null) {
            throw new BusinessException("No se pudo registrar el contribuyente.");
        }

        ApoderadoRequest apo = mapApoderado(dto, respContri.getCodigo(), req);
        ApoderadoResponse respApo = apoderadoRepository.registrarApoderado(apo);

        if (respApo == null) {
            throw new BusinessException("No se pudo registrar el apoderado.");
        }

        log.info("Apoderado registrado correctamente: {}", respContri.getCodigo());

        return respApo;

    }

    private ContribuyenteRequest mapContribuyente(GeneralRequest dto, HttpServletRequest req) {

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
        c.setEstacion(extractClientIp(req));

        return c;
    }

    private ApoderadoRequest mapApoderado(GeneralRequest dto, String codigo, HttpServletRequest req) {

        ApoderadoRequest a = new ApoderadoRequest();
        a.setOpcion(2);
        a.setCodigo(codigo);
        a.setIdTipoDocumento(dto.getTipoDocumento());
        a.setNumDocumento(dto.getNumeroDocumento());
        a.setOperador("WEB_TALLERES");
        a.setEstacion(extractClientIp(req));
        a.setPassword(passwordEncoder.encode(dto.getPassword()));

        return a;
    }

    @Override
    public String extractClientIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "CF-Connecting-IP",
                "True-Client-IP",
                "Forwarded"
        };

        for (String h : headers) {
            String v = request.getHeader(h);
            if (v != null && !v.isBlank()) return v.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }

}
