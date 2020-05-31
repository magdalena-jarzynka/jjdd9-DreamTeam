package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Translator;

import javax.ejb.Local;

@Local
public interface TranslatorRepository {
    void save(Translator translator);

    Translator update(Translator translator);
}