package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class KindRepositoryBean implements KindRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save (Kind kind) {entityManager.persist(kind);}

    @Override
    public Kind update (Kind kind) {
        return entityManager.merge(kind);
    }

    @Override
    public Optional<Kind> findByName(String name) {
        Query query = entityManager.createNamedQuery("Kind.findKindByName");
        query.setParameter("name", name);
        return query.getResultList().stream().findAny();
    }
}
