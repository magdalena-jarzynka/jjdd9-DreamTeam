package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Translator;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface TranslatorRepository {
    void save(Translator translator);

    Translator update(Translator translator);

    Optional<Translator> findByName(String name);
}
