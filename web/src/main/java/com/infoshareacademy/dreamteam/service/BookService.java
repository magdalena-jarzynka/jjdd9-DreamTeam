package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.dao.BookDao;
import com.infoshareacademy.dreamteam.domain.entity.Book;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BookService {

    @EJB
    BookDao bookDao;

    public Book findBookById(Long id) {
        return bookDao.findBookById(id).orElseThrow();
    }

}
