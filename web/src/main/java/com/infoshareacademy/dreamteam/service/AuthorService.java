package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.api.AuthorPlain;
import com.infoshareacademy.dreamteam.repository.AuthorRepository;

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
public class AuthorService {
    @EJB
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }

    public Author update(Author author) {
        return authorRepository.update(author);
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    public List<AuthorPlain> parseAuthorsFromApi(String url) throws IOException {
        Client client = ClientBuilder.newClient();
        return client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class)
                .readEntity(new GenericType<List<AuthorPlain>>() {
                });
    }


}
