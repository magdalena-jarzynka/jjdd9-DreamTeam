package com.infoshareacademy.dreamteam.email;

public class FavouriteBookEmailBuilder implements EmailMessageBuilder{
    public static final String EMAIL_SUBJECT = "Książka z twojej listy ulubionych została usunięta";
    public static final String EMAIL_BODY = "Szanowny czytelniku/Szanowna czytelniczko, %n%n" +
            " Z przykrością informujemy, że książka \"%s\" z twojej listy ulubionych została usunięta " +
            "z bazy danych biblioteki %n%n" +
            "Życzymy miłego dnia, załoga DreamTeam.";

    private String var;

    public FavouriteBookEmailBuilder(String var) {
        this.var = var;
    }

    @Override
    public String buildSubject() {
        return EMAIL_SUBJECT;
    }

    @Override
    public String buildContent() {
        return String.format(EMAIL_BODY, var);
    }
}
