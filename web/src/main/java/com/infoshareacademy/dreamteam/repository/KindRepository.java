package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface KindRepository {
    void save(Kind kind);

    Kind update(Kind kind);

    Optional<Kind> findByName(String name);
}
