package pe.gob.mlvictoria.complejo.service.Impl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminGeneRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminRequest;
import pe.gob.mlvictoria.complejo.dto.administrado.ComAdminResponse;
import pe.gob.mlvictoria.complejo.repository.ComAdminRepository;
import pe.gob.mlvictoria.complejo.service.ComAdminService;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteRequest;
import pe.gob.mlvictoria.talleres.dto.apoderado.ContribuyenteResponse;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.talleres.repository.ApoderadoRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComAdminServiceImpl implements ComAdminService {

    private final ComAdminRepository comAdminRepository;
    private final ApoderadoRepository apoderadoRepository;

    @Override
    public ComAdminResponse registrarComAdmin(ComAdminGeneRequest dto,HttpServletRequest req) {
        ContribuyenteRequest contrib = mapContribuyente(dto, req);
        ContribuyenteResponse respContri = apoderadoRepository.registrarContribuyente(contrib);
        if (respContri == null || respContri.getCodigo() == null) {
            throw new BusinessException("No se pudo registrar el contribuyente.");
        }
        ComAdminRequest comAdmin = mapComAdmin(dto, respContri.getCodigo(), req);
        ComAdminResponse respComAdmin = comAdminRepository.registrarComAdministrado(comAdmin);
        if (respComAdmin == null) {
            throw new BusinessException("No se pudo registrar el administrado de comercio.");
        }
        return respComAdmin;
    }

    private ComAdminRequest mapComAdmin(ComAdminGeneRequest dto, String codigoContribuyente, HttpServletRequest req) {
        ComAdminRequest c = new ComAdminRequest();
        c.setOpcion(1);
        c.setTipoPersona(dto.getTipoPersona());
        c.setTipoDocumento( Integer.parseInt(dto.getTipoDocumento()));
        c.setCodigo(codigoContribuyente);
        c.setNumDocumento(dto.getNumeroDocumento());
        c.setConAcepto(dto.getConAcepto());
        c.setConNoResonsabilizar(dto.getConNoResonsabilizar());
        c.setOperador("WEB_COMPLEJO");
        c.setEstacion(extractClientIp(req));

        return c;
    }

    private ContribuyenteRequest mapContribuyente(ComAdminGeneRequest dto, HttpServletRequest req) {
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
        c.setOperador("WEB_COMPLEJO");
        c.setEstacion(extractClientIp(req));

        return c;
    }

    private String extractClientIp(HttpServletRequest request) {
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
