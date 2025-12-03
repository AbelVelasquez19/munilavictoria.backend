package pe.gob.mlvictoria.pagolinea.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.pagolinea.dto.*;
import pe.gob.mlvictoria.pagolinea.model.ContribuyenteLogin;
import pe.gob.mlvictoria.pagolinea.repository.ContribuyenteRepository;
import pe.gob.mlvictoria.pagolinea.service.ContribuyenteService;
import pe.gob.mlvictoria.pagolinea.service.EmailService;
import pe.gob.mlvictoria.pagolinea.service.EmailTemplateService;

import java.util.Map;

@Service
public class ContribuyenteServiceImpl implements ContribuyenteService {

    @Autowired
    private ContribuyenteRepository contribuyenteRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Override
    public RecuperarContrasenaResponseDTO recuperarContrasena(ContribuyenteDTO dto) {
        return contribuyenteRepository.recuperarContrasena(dto);
    }

    @Override
    public EnviarCodigoRecuperacionResponseDTO enviarCodigoRecuperacion(ContribuyenteDTO dto) {
        String mensajeCuerpo = "Un último paso para actualizar y/o recuperar su contraseña. Para validar, copie y pegue el siguiente enlace en su navegador:";
        if(dto.getOpcion()==5){
            EnviarCodigoRecuperacionResponseDTO response = contribuyenteRepository.enviarCodigoRecuperacion(dto);
            if ("400".equals(response.getRescode())){
                return response;
            }
            String val = response.getVal();
            String urlValidacion = "https://pagoenlinea.munilavictoria.gob.pe/enviar-nueva-contrasena?validacion=" + val;

            Map<String, String> variables = Map.of(
                    "codigoConfirmacion", val,
                    "urlValidacion", urlValidacion,
                    "mensajeCuerpo",mensajeCuerpo,
                    "administrado","Contribuyente"
            );

            String template = emailTemplateService.buildTemplate("recuperacion-codigo.html", variables);

            if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
                emailService.sendEmail(dto.getEmail(), dto.getNombres(), "Código de Verificación", template);
            } else {
                throw new IllegalArgumentException("El correo electrónico es requerido");
            }

            return response;
        }
        else if(dto.getOpcion()==1){
            EnviarCodigoRecuperacionResponseDTO response = contribuyenteRepository.actualizarRecuperarContrasena(dto);
            if ("400".equals(response.getRescode())){
                return response;
            }
            String val = response.getVal();
            String urlValidacion = "https://pagoenlinea.munilavictoria.gob.pe/enviar-nueva-contrasena?validacion=" + val;
            Map<String, String> variables = Map.of(
                    "codigoConfirmacion", val,
                    "urlValidacion", urlValidacion,
                    "mensajeCuerpo",mensajeCuerpo,
                    "administrado","Contribuyente"
            );

            String template = emailTemplateService.buildTemplate("recuperacion-codigo.html", variables);

            if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
                emailService.sendEmail(dto.getEmail(), dto.getNombres(), "Código de Verificación", template);
            } else {
                throw new IllegalArgumentException("El correo electrónico es requerido");
            }
            return response;

        }{
            throw new IllegalArgumentException("Opción no válida: " + dto.getOpcion());
        }
    }

    @Override
    public EnviarCodigoRecuperacionResponseDTO enviaNuevaContrasena(ContribuyenteDTO dto){
        if(dto.getOpcion()== 3){
            EnviarCodigoRecuperacionResponseDTO response = contribuyenteRepository.enviaNuevaContrasena(dto);
            if ("400".equals(response.getRescode())){
                return response;
            }

            String urlLogin = "https://pagoenlinea.munilavictoria.gob.pe/login";
            String mensajeCuerpo = "Aqui tiene su nueva contraseña";
            Map<String, String> variables = Map.of(
                    "codigoConfirmacion", response.getVal(),
                    "urlValidacion", urlLogin,
                    "mensajeCuerpo",mensajeCuerpo,
                    "administrado","Contribuyente"

            );

            String template = emailTemplateService.buildTemplate("recuperacion-codigo.html", variables);

            if(response.getEmail() != null && !response.getEmail().isBlank()){
                emailService.sendEmail(response.getEmail(),"Abel Velasquez", "Código de Verificación", template);
            } else {
                throw new IllegalArgumentException("El correo electrónico es requerido");
            }

            return response;
        }
        {
            throw new IllegalArgumentException("Opción no válida");
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        ContribuyenteLogin usuario = contribuyenteRepository.login(loginRequest.getCodigo(), loginRequest.getPassword());
        if (usuario == null) {
            return new LoginResponseDTO(null, null,null,null,null, "400");
        }
        return new LoginResponseDTO(
                usuario.getCodigo(),
                usuario.getDniruc(),
                usuario.getNum_duc(),
                usuario.getNombres(),
                usuario.getToken(),
                "200"
        );
    }

    @Override
    public AnioPenContriRespondeDTO anioPendientePagar(AnioPenContriRespondeDTO dto) {
        return contribuyenteRepository.anioPendientePagar(dto);
    }
}
