package pe.gob.mlvictoria.complejo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mlvictoria.complejo.dto.pago.*;
import pe.gob.mlvictoria.complejo.repository.PagoRepository;
import pe.gob.mlvictoria.complejo.service.PagoService;
import pe.gob.mlvictoria.pagolinea.service.EmailService;
import pe.gob.mlvictoria.pagolinea.service.EmailTemplateService;
import pe.gob.mlvictoria.talleres.exepcion.BusinessException;
import pe.gob.mlvictoria.utility.ComplejoConfig;
import pe.gob.mlvictoria.utility.QrGeneratorUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private ComplejoConfig visaConfig;

    @Override
    public BuscarTokenResponse buscarTokenPago(BuscarTokenRequest dto) {
        if(dto.getIdToken() == null || dto.getIdToken().isEmpty()){
            throw new BusinessException("tiken no puede ser nulo o vacio");
        }
        BuscarTokenResponse response = pagoRepository.buscarTokenPago(dto);
        if(response == null) {
            throw new BusinessException("No se encontro token");
        } else {
            return response;
        }
    }

    @Override
    public TicketAprobadoResponse ticketAprobado(TicketAprobadoRequest dto) {
        if(dto.getIdToken() == null || dto.getIdToken().isEmpty()){
            throw new BusinessException("tiken no puede ser nulo o vacio");
        }
        TicketAprobadoResponse response = pagoRepository.ticketAprobado(dto);
        if(response == null) {
            throw new BusinessException("No se encontro token");
        } else {
            return response;
        }
    }

    @Override
    public boolean enviarTicketAprobado(TicketAproResulResponse response, String idToken) {
            if (response.getIdReserva() == null){
                throw new BusinessException("id de reserva no puede ser nulo o vacio");
            }

            StringBuilder horariosHtml = new StringBuilder();

            if (response.getDetallesJson() != null) {
                response.getDetallesJson().forEach(det -> {
                    horariosHtml.append("⦿ <b>")
                            .append(det.getRango())
                            .append("</b> — S/ ")
                            .append(det.getPrecio())
                            .append("<br>");
                });
            }

            Map<String, String> variables = new HashMap<>();
            variables.put("purchaseNumber", response.getPurchaseNumber());
            variables.put("fechaOperacion", response.getFechaReserva()+" "+new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
            variables.put("nombreCompleto", response.getNombreAdministrado());
            variables.put("dni", response.getNumeroDocumento());
            variables.put("celular", response.getCelular());
            variables.put("canchaNombre", response.getComplejo());
            variables.put("canchaTipo", response.getCancha());
            variables.put("fechaReserva", response.getFechaReserva());
            variables.put("totalPagado",response.getMontoTotal().toString());
            variables.put("codigoReserva", response.getPurchaseNumber());

            variables.put("horariosLista", horariosHtml.toString());


            byte[] qrImage = QrGeneratorUtil.generateQrBytes(visaConfig.getUrl().getUrlFrontend()+"/inicio?tid="+idToken);

            String template = emailTemplateService.buildTemplate("complejo/pago-aprobado.html", variables);

            if(response.getCorreo() != null && !response.getCorreo().isBlank()){
                emailService.sendEmailWithQr(response.getCorreo(),response.getNombreAdministrado(), "Su reserva fue aprobado correctamente", template,qrImage);
            } else {
                throw new IllegalArgumentException("El correo electrónico es requerido");
            }

            return true;
    }

    @Override
    public boolean enviarTicketRechazado(BuscarTokenResponse response, String correo) {
        if (response.getId() == null){
            throw new BusinessException("id de reserva no puede ser nulo o vacio");
        }
        Map<String, String> variables = new HashMap<>();
        AuthRawResponse auth = (AuthRawResponse) response.getAuthRaw();
        DataSectionResponse data = auth.getData();

        variables.put("purchaseNumber", response.getPurchaseNumber());
        variables.put("fechaOperacion", new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()));
        variables.put("motivoRechazo",auth.getErrorMessage());
        variables.put("visaCodigo",data.getActionCode());
        variables.put("visaAutorizacion",data.getActionDescription());
        variables.put("visaDescripcion",data.getEciDescription()!=null ? data.getEciDescription() : "N/A");
        variables.put("visaTraza",data.getTraceNumber());
        variables.put("totalPagado",response.getAmount().toString());
        variables.put("marcaTarjeta",data.getBrand());
        variables.put("tarjetaEnmascarada",data.getCard());

        String template = emailTemplateService.buildTemplate("complejo/pago-rechazado.html", variables);

        if(correo != null && !correo.isBlank()){
            emailService.sendEmail(correo,"Velasquez", "Su reserva fue rechazado", template);
        } else {
            throw new IllegalArgumentException("El correo electrónico es requerido");
        }
        return true;
    }

}
