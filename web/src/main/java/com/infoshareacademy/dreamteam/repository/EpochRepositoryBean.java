package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class EpochRepositoryBean implements EpochRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Epoch epoch) {entityManager.persist(epoch);}

    @Override
    public Epoch update (Epoch epoch) {
        return entityManager.merge(epoch);
    }

    @Override
    public Optional<Epoch> findByName(String name) {
        Query query = entityManager.createNamedQuery("Epoch.findEpochByName");
        query.setParameter("name", name);
        return query.getResultList().stream().findAny();
    }
}
