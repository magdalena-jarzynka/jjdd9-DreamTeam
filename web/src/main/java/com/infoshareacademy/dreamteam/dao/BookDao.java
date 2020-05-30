package com.infoshareacademy.dreamteam.dao;


import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface BookDao {

    Optional<Book> findBookById(Long id);

}
