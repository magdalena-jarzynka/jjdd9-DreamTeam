//package com.infoshareacademy.dreamteam.repository;
//
//import com.infoshareacademy.dreamteam.domain.entity.Author;
//import com.infoshareacademy.dreamteam.domain.entity.Epoch;
//import com.infoshareacademy.dreamteam.domain.entity.Translator;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.Optional;
//
//@Stateless
//public class TranslatorRepositoryBean implements TranslatorRepository{
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public void save (Translator translator) {entityManager.persist(translator);}
//
//    @Override
//    public Translator update (Translator translator) {
//        return entityManager.merge(translator);
//    }
//
//    @Override
//    public Optional<Translator> findByName(String name) {
//        Query query = entityManager.createNamedQuery("Translator.findTranslatorByName");
//        query.setParameter("name", name);
//        return query.getResultList().stream().findAny();
//    }
//}
