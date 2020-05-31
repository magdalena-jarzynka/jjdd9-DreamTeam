package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Translator;
import com.infoshareacademy.dreamteam.repository.TranslatorRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TranslatorService {
    @EJB
    private TranslatorRepository translatorRepository;

    public void save(Translator translator) {
        translatorRepository.save(translator);
    }

    public Translator update(Translator translator) {
        return translatorRepository.update(translator);
    }

    public Translator findByName(String name) {
        Translator translator = translatorRepository.findByName(name).orElse(null);
        return translator;
    }
}
