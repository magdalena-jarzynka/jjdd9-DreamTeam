package com.infoshareacademy.dreamteam.service;


import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Rating;
import com.infoshareacademy.dreamteam.repository.BookRepository;
import com.infoshareacademy.dreamteam.repository.RatingRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RatingService {

    @EJB
    private RatingRepository ratingRepository;

    @EJB
    private BookRepository bookRepository;

    public void addRating(Long bookId) {
        Book book = bookRepository.findBookById(bookId).get();
        Rating rating = findByBook(book);
        rating.setBook(book);
        ratingRepository.save(rating);
    }

    public Double calculateAverageRating(Rating rating) {
        return (double) rating.getSumOfVotes() / (double) rating.getNumberOfVotes();
    }

    public Rating findByBook(Book book) {
        return ratingRepository.findByBook(book).orElseThrow();
    }

}
