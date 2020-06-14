package com.infoshareacademy.dreamteam.email;

import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;

@Stateless
public class EmailManager {

    private static final Logger logger = LoggerFactory.getLogger(EmailManager.class.getName());
    public static final String MAIL_TITLE = "Potwierdź rezerwację swojej książki";
    //ZMIEŃ NA LOCALHOSTA NA CZAS TESTOWANIA LOKALNIE
//    public static final String BASE_URL = "http://localhost:8080/confirm?token=";
//    public static final String BASE_URL = "http://localhost:4390/confirm?token=";
    public static final String BASE_URL = "http://dreamteam.jjdd9.is-academy.pl/confirm?token=";


    public void sendEmail(ReservationRequest reservationRequest) {
        final String username = getProperty("username");
        final String password = getProperty("password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.mime.allowutf8", true);


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            Multipart multiPart = new MimeMultipart("alternative");
            message.setFrom(new InternetAddress("jjdd9dt@gmail.com"));
            UserView userView = reservationRequest.getUserView();
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userView.getEmail())
            );

            BookView bookView = reservationRequest.getBookView();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Szanowny czytelniku/Szanowna czytelniczko, \n\n" +
                    "Chcąc potwierdzić rezerwację książki: " + "\"" + bookView.getTitle() +
                    "\"" + " kliknij w poniższy link w ciągu najbliższych 15 minut: \n" +
                    BASE_URL + reservationRequest.getToken() +
                    "\n\nŻyczymy miłego dnia, załoga DreamTeam.", "utf-8");
            multiPart.addBodyPart(textPart);
            try {
                message.setSubject(MimeUtility.encodeText(MAIL_TITLE, "utf-8", "B"));
            } catch (UnsupportedEncodingException e) {
                logger.info(String.format("Problem occurred when encoding the email title: \" %s", e.getMessage()));
            }
            message.setContent(multiPart);
            Transport.send(message);

        } catch (MessagingException e) {
            logger.info(String.format("Problem occurred when sending the confirmation email: %s", e.getMessage()));
        }
    }

    private String getProperty(String property) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Thread.currentThread()
                    .getContextClassLoader().getResource("email.properties"))
                    .openStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties.getProperty(property);
    }

}
