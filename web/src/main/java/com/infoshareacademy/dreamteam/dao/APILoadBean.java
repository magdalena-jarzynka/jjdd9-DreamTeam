package com.infoshareacademy.dreamteam.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import com.infoshareacademy.dreamteam.domain.pojo.BookUrl;
import com.infoshareacademy.dreamteam.service.LoadJsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Singleton
@Startup
public class APILoadBean {
    private static final Logger logger = LoggerFactory.getLogger(APILoadBean.class.getName());
    private static final String url = "https://wolnelektury.pl/api/books/?format=json";
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(url);

    @PostConstruct
    public void loadBooksFromAPI() throws IOException {
        LoadJsonService loadJsonService = new LoadJsonService();
        List<BookUrl> urls = getURLList(url);
        for (BookUrl bookUrl : urls) {
            loadJsonService.loadDatabase(loadJsonService.loadFromURL(new URL(bookUrl.getHref() + "?format=json")));
        }
    }

    public List<BookUrl> getURLList(String url) {
        ObjectMapper mapper = new ObjectMapper();
        List<BookUrl> urls;
        try {
            urls = mapper.readValue(new URL(url), new TypeReference<List<BookUrl>>() {
            });
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return List.of();
        }
        return urls;
    }


}