package pe.gob.mlvictoria.pagolinea.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String destinatario, String nombre, String asunto, String contenidoHtml) {
        if (destinatario == null || destinatario.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("nocontestar@munilavictoria.gob.pe", "MUNICIPALIDAD DE LA VICTORIA");
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true);
            helper.addBcc("nocontestar@munilavictoria.gob.pe");

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un problema al enviar el correo.", e);
        }
    }
}
