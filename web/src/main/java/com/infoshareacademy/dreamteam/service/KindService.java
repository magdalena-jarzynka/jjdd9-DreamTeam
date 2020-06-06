package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Kind;
import com.infoshareacademy.dreamteam.domain.pojo.KindPlain;
import com.infoshareacademy.dreamteam.repository.KindRepository;

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
public class KindService {
    @EJB
    private KindRepository kindRepository;

    public void save(Kind Kind) {
        kindRepository.save(Kind);
    }

    public Kind update(Kind kind) {
        return kindRepository.update(kind);
    }

    public Kind findByName(String name) {
        return kindRepository.findByName(name).orElse(null);
    }

    public List<KindPlain> parseKindsFromApi(String url) throws IOException {
        Client client = ClientBuilder.newClient();
        return client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class)
                .readEntity(new GenericType<List<KindPlain>>() {
                });
    }

}
