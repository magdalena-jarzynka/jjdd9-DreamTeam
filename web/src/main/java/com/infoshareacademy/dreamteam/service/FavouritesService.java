package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class FavouritesService {

    @EJB
    UserService userservice;

    @EJB
    BookService bookService;

    @Transactional
    public boolean addBookToFavourites(Long bookId, Long userId) {
        User user = userservice.findUserById(userId);
        Book book = bookService.findById(bookId);
        if(user.getFavourites().contains(book)) {
            return false;
        } else {
            user.getFavourites().add(book);
            book.getFavourites().add(user);
            userservice.update(user);
            bookService.update(book);
            return true;
        }
    }

    @Transactional
    public boolean removeBookFromFavourites(Long bookId, Long userId) {
        User user = userservice.findUserById(userId);
        Book book = bookService.findById(bookId);
        if(user.getFavourites().contains(book)) {
            user.getFavourites().remove(book);
            book.getFavourites().remove(user);
            userservice.update(user);
            bookService.update(book);
            return true;
        } else {
            return false;
        }
    }
}
