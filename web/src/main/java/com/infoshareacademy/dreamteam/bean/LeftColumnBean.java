package com.infoshareacademy.dreamteam.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class LeftColumnBean {

    private Map<String, Object> leftColumn = new HashMap<>();

    @PostConstruct
    public void init() {
        leftColumn.put("main_page", LeftColumnTag.MAIN_PAGE.getTagDescription());
        leftColumn.put("browse", LeftColumnTag.BROWSE.getTagDescription());
        leftColumn.put("search", LeftColumnTag.SEARCH.getTagDescription());
        leftColumn.put("genres", LeftColumnTag.GENRES.getTagDescription());
        leftColumn.put("favourites", LeftColumnTag.FAVOURITES.getTagDescription());
        leftColumn.put("reservations", LeftColumnTag.RESERVATIONS.getTagDescription());
        leftColumn.put("stats", LeftColumnTag.STATS.getTagDescription());
        leftColumn.put("manage", LeftColumnTag.MANAGE.getTagDescription());
    }


    public Map<String, Object> getLeftColumn() {
        return leftColumn;
    }
}
