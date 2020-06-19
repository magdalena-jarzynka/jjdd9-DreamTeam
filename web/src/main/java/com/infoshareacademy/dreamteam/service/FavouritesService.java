package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.email.BookReservationEmailBuilder;
import com.infoshareacademy.dreamteam.email.EmailManager;
import com.infoshareacademy.dreamteam.email.FavouriteBookEmailBuilder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class FavouritesService {

    @EJB
    UserService userService;

    @EJB
    BookService bookService;

    @Inject
    private EmailManager emailManager;

    @Transactional
    public boolean addBookToFavourites(Long bookId, Long userId) {
        User user = userService.findUserById(userId);
        Book book = bookService.findById(bookId);
        if(user.getFavourites().contains(book)) {
            return false;
        } else {
            user.getFavourites().add(book);
            book.getFavourites().add(user);
            userService.update(user);
            bookService.update(book);
            return true;
        }
    }

    @Transactional
    public boolean removeBookFromFavourites(Long bookId, Long userId) {
        User user = userService.findUserById(userId);
        Book book = bookService.findById(bookId);
        if(user.getFavourites().contains(book)) {
            user.getFavourites().remove(book);
            book.getFavourites().remove(user);
            userService.update(user);
            bookService.update(book);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void favouriteRemoval(Long bookId) {
        Book book = bookService.findById(bookId);
        for(User user: userService.findAll()) {
            List<Long> favouritesId = userService.getFavourites(user.getId()).stream()
                    .map(Book::getId).collect(Collectors.toList());
            if(favouritesId.contains(bookId)) {
                removeBookFromFavourites(bookId, user.getId());
                emailManager.sendEmail(new FavouriteBookEmailBuilder(book.getTitle()), user.getEmail());
            }
        }
    }
}
