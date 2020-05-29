package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.entity.Translator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TranslatorRepositoryBean implements TranslatorRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Translator translator) {entityManager.persist(translator);}

    @Override
    public Translator update (Translator translator) {
        return entityManager.merge(translator);
    }

}
