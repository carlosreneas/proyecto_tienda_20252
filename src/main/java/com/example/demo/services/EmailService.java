package com.example.demo.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void enviarCorreoConTemplate(String to, String subject, String templateName, Map<String, Object> variables)
            throws MessagingException {

        // Crear contexto de Thymeleaf
        Context context = new Context();
        context.setVariables(variables);

        // Procesar plantilla
        String contenidoHtml = templateEngine.process(templateName, context);

        // Crear mensaje MIME
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(contenidoHtml, true); // true para contenido HTML

        mailSender.send(mensaje);
    }
}
