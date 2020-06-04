package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Kind;
import com.infoshareacademy.dreamteam.repository.KindRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class KindService {
    @EJB
    private KindRepository kindRepository;

    public void save(Kind kind) {
        kindRepository.save(kind);
    }

    public Kind update(Kind kind) {
        return kindRepository.update(kind);
    }

    public Kind findByName(String name) {
        return kindRepository.findByName(name).orElse(null);
    }
}
