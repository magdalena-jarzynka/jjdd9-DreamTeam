package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.repository.EpochRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

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
}
