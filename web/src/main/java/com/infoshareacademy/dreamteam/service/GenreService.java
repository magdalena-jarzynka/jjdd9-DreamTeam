package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.domain.pojo.GenrePlain;
import com.infoshareacademy.dreamteam.repository.GenreRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@RequestScoped
public class GenreService {
    @EJB
    private GenreRepository genreRepository;

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.update(genre);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name).orElse(null);
    }

    public List<GenrePlain> parseGenresFromApi(String url) throws IOException {
        Client client = ClientBuilder.newClient();
        return client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class)
                .readEntity(new GenericType<List<GenrePlain>>() {
                });
    }

}
