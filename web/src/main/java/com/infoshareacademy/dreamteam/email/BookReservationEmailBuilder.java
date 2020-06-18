package com.infoshareacademy.dreamteam.email;

public class BookReservationEmailBuilder implements EmailMessageBuilder {

    public static final String EMAIL_SUBJECT = "Potwierdź rezerwację swojej książki";
    public static final String EMAIL_BODY = "Szanowny czytelniku/Szanowna czytelniczko, %n%n" +
            "Chcąc potwierdzić rezerwację książki: \"%s\"" +
            " kliknij w poniższy link w ciągu najbliższych 15 minut: %n%s %n%n" +
            "Życzymy miłego dnia, załoga DreamTeam.";

    private String[] var;

    public BookReservationEmailBuilder(String... var) {
        this.var = var;
    }

    @Override
    public String buildSubject() {
        return EMAIL_SUBJECT;
    }

    @Override
    public String buildContent() {
        return String.format(EMAIL_BODY, var[0], var[1]);

    }

}
