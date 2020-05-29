package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Kind;

import javax.ejb.Local;

@Local
public interface KindRepository {
    void save(Kind kind);

    Kind update(Kind kind);
}
