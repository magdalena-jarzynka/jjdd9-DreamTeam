package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.domain.entity.Epoch;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface EpochRepository {
    void save(Epoch epoch);

    Epoch update(Epoch epoch);

    Optional<Epoch> findByName(String name);
}
