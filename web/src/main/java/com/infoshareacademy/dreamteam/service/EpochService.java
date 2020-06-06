package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.pojo.EpochPlain;
import com.infoshareacademy.dreamteam.repository.EpochRepository;

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
public class EpochService {
    @EJB
    private EpochRepository epochRepository;

    public void save(Epoch epoch) {
        epochRepository.save(epoch);
    }

    public Epoch update(Epoch epoch) {
        return epochRepository.update(epoch);
    }

    public Epoch findByName(String name) {
        return epochRepository.findByName(name).orElse(null);
    }

    public List<EpochPlain> parseEpochsFromApi(String url) throws IOException {
        Client client = ClientBuilder.newClient();
        return client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class)
                .readEntity(new GenericType<List<EpochPlain>>() {
                });
    }

}
