package com.infoshareacademy.dreamteam.layout.builder;

import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class LeftColumnBuilder {

    public static Map<String, Object> build() {
        Map<String, Object> leftColumn = new HashMap<>();
        leftColumn.put("main_page", LeftColumnMenuList.MAIN_PAGE.getLabel());
        leftColumn.put("browse", LeftColumnMenuList.BROWSE.getLabel());

        leftColumn.put("favourites", LeftColumnMenuList.FAVOURITES.getLabel());
        leftColumn.put("reservations", LeftColumnMenuList.RESERVATIONS.getLabel());

        leftColumn.put("stats", LeftColumnMenuList.STATS.getLabel());
        leftColumn.put("manage", LeftColumnMenuList.MANAGE.getLabel());
        leftColumn.put("accounts", LeftColumnMenuList.ACCOUNTS.getLabel());

        return leftColumn;
    }

}
