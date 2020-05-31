package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.view.BookView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BookMapper {

    public BookView mapEntityToView(Book book) {
        BookView bookView = new BookView();
        bookView.setId(book.getId());
        bookView.setTitle(book.getTitle());
        bookView.setAudio(book.getAudio());
        bookView.setFragment(book.getFragment());
        bookView.setIsbn(book.getIsbn());
        if (book.getCover() == null || book.getCover().isEmpty()) {
            bookView.setCover("/images/missing-cover.png");
        } else {
            bookView.setCover(book.getCover());
        }
        return bookView;
    }

}