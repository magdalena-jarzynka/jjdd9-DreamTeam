package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;

@Local
public interface BookRepository {
    void save (Book book);
}
