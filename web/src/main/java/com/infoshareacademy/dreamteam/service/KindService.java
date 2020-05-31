package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Kind;
import com.infoshareacademy.dreamteam.repository.KindRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

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
        Kind kind = kindRepository.findByName(name).orElse(null);
        return kind;
    }
}
