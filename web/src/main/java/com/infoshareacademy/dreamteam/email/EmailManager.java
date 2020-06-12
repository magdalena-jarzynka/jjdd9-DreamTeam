package com.infoshareacademy.dreamteam.email;

import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class EmailManager {

    private static final Logger logger = LoggerFactory.getLogger(EmailManager.class.getName());

    //TODO CO PRZYJMUJE FUNKCJA
//    public void sendEmail(ZASTANOWIĆ SIĘ) {
    public void sendEmail(ReservationRequest reservationRequest) {
        final String username = "jjdd9dtmail@gmail.com";
//        final String password = "dT997g3F";
        final String password = "nkwzaglybaqxcbuw";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jjdd9dt@gmail.com"));
            UserView userView = reservationRequest.getUserView();
            message.setRecipients(
                    Message.RecipientType.TO,
                    //TODO TUTAJ PODAĆ ADRES ODBIORCY user.getEmail()
//                    InternetAddress.parse(OOOO TUTAJ)
                    InternetAddress.parse(userView.getEmail())
            );

            //TODO TUTAJ PODAĆ CO MA BYĆ W MAILU
            message.setSubject("Potwierdź rezerwację swojej książki");
            message.setText("Próba 1");

            Transport.send(message);

        } catch (MessagingException e) {
            logger.info(String.format("Problem occured when sending the confirmation email: %s", e.getMessage()));
        }

    }
}
