package pe.gob.mlvictoria.talleres.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.talleres.dto.restablecer.*;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.talleres.repository.RestablecerRepository;
import pe.gob.mlvictoria.talleres.service.RestablecerService;
import pe.gob.mlvictoria.utility.CorreoService;
import pe.gob.mlvictoria.utility.CorreoTemplateService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestablecerServiceImpl implements RestablecerService {

    private final RestablecerRepository restablecerRepository;
    private final CorreoTemplateService correoTemplateService;
    private final CorreoService correoService;

    @Override
    public RestablecerResponse buscarUsuarioCorreo(BusUsuCorreoRequest dto) {
        if (dto == null || dto.getCorreo() == null || dto.getCorreo().isEmpty()) {
            throw new BusinessException("Correo es requerido.");
        }

        RestablecerRequest restablecerRequest = new RestablecerRequest();
        restablecerRequest.setOpcion(1);
        restablecerRequest.setCorreo(dto.getCorreo());
        RestablecerResponse response = restablecerRepository.restablecerPassword(restablecerRequest);

        if (response == null) {
            throw new BusinessException("No se encontró usuario con el correo proporcionado.");
        }

        RestablecerRequest restablecerRequest1 = new RestablecerRequest();
        restablecerRequest1.setOpcion(2);
        restablecerRequest1.setCorreo(response.getEmail());
        restablecerRequest1.setCodigo(response.getCodigo());
        restablecerRequest1.setNumDoc(response.getNumDoc());

        RestablecerResponse response1 = restablecerRepository.restablecerPassword(restablecerRequest1);

        if (response1 == null) {
            throw new BusinessException("Error al generar token.");
        }

        String urlLogin = "http://localhost:4400/restablecer?validacion="+response1.getToken();
        String mensajeCuerpo = "Un último paso para actualizar y/o recuperar su contraseña. Para validar, copie y pegue el siguiente enlace en su navegador:";
        Map<String, String> variables = Map.of(
                "codigoConfirmacion", response1.getToken(),
                "urlValidacion", urlLogin,
                "mensajeCuerpo",mensajeCuerpo,
                "administrado","Administrado"
        );
        String template = correoTemplateService.buildTemplateCorreo("recuperacion-codigo.html", variables);
        correoService.sendCorreo(dto.getCorreo(), "Administrado", "Código de Verificación", template);
        return response1;
    }

    @Override
    public RestablecerResponse validarToken(TokenRequest dto) {
        if (dto == null || dto.getToken() == null || dto.getToken().isEmpty()) {
            throw new BusinessException("Token es requerido.");
        }
        try{
            RestablecerRequest restablecerRequest = new RestablecerRequest();
            restablecerRequest.setOpcion(3);
            restablecerRequest.setTokenn(dto.getToken());
            RestablecerResponse response = restablecerRepository.restablecerPassword(restablecerRequest);
            if (response == null) {
                throw new BusinessException("Token inválido o expirado.");
            }
            return response;
        }catch (Exception ex) {
            String msg = ex.getMessage();
            if (msg != null && msg.contains("Token inválido")) {
                throw new BusinessException("Token inválido");
            }

            if (msg != null && msg.contains("El token ha expirado")) {
                throw new BusinessException("El token ha expirado");
            }

            if (msg != null && msg.contains("El token ya fue utilizado")) {
                throw new BusinessException("Token ya fue utilizado");
            }
            throw ex;
        }
    }

    @Override
    public RestablecerResponse actualizarPassword(ActPassRequest dto) {
        if (dto.getToken() == null || dto.getToken().isEmpty()) {
            throw new BusinessException("Token es requerido.");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new BusinessException("Password es requerido.");
        }
        if (dto.getConfirmPassword() == null || dto.getConfirmPassword().isEmpty()) {
            throw new BusinessException("Confirmar Password es requerido.");
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new BusinessException("Las contraseñas no coinciden.");
        }
        // validar minimo y maximo de password de 8 digitos
        if (dto.getPassword().length() < 8 || dto.getPassword().length() > 10) {
            throw new BusinessException("La contraseña debe tener  mínimo 8 y máximo 10 caracteres.");
        }
        try{
            RestablecerRequest restablecerRequest = new RestablecerRequest();
            restablecerRequest.setOpcion(4);
            restablecerRequest.setTokenn(dto.getToken());
            restablecerRequest.setPassword(dto.getPassword());
            RestablecerResponse response = restablecerRepository.restablecerPassword(restablecerRequest);
            if (response == null) {
                throw new BusinessException("Error al actualizar la contraseña.");
            }
            return response;
        }catch (Exception ex) {
            String msg = ex.getMessage();
            if (msg != null && msg.contains("Token inválido")) {
                throw new BusinessException("Token inválido");
            }
            if (msg != null && msg.contains("Password Actualizado")) {
                throw new BusinessException("Ya se actualizó la contraseña con este token");
            }
            throw ex;
        }
    }
}
