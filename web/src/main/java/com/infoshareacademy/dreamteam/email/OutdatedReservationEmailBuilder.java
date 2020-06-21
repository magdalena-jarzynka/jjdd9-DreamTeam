package com.infoshareacademy.dreamteam.email;

public class OutdatedReservationEmailBuilder implements EmailMessageBuilder {

    public static final String EMAIL_SUBJECT = "Twoja rezerwacja wygasła";
    public static final String EMAIL_BODY = "Szanowny czytelniku/Szanowna czytelniczko, %n%n" +
            "Twoja rezerwacja książki: \"%s\"" + " wygasła po upływie 48 godzin.%n" +
            "Jeżeli wciąż jesteś zainteresowany/zainteresowana tą pozycją - ponownie dokonaj rezerwacji. %n%n" +
            "Życzymy miłego dnia, załoga DreamTeam.";

    private String[] var;


    public OutdatedReservationEmailBuilder(String... var) {
        this.var = var;
    }

    @Override
    public String buildSubject() {
        return EMAIL_SUBJECT;
    }

    @Override
    public String buildContent() {
        return String.format(EMAIL_BODY, var[0]);
    }
}
