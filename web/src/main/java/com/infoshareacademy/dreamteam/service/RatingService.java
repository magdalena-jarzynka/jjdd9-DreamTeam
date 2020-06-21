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

    public void addRating(Long bookId, int rate) {
        Book book = bookRepository.findBookById(bookId).get();
        Rating rating = (findByBookId(bookId));
        rating.setBook(book);
        rating.setNumberOfVotes(rating.getNumberOfVotes() + 1);
        rating.setSumOfVotes(rating.getSumOfVotes() + rate);
        ratingRepository.update(rating);
    }

    public Double calculateAverageRating(Rating rating) {
        if (rating.getNumberOfVotes() == 0) {
            return 0.0;
        }
        return (double) rating.getSumOfVotes() / (double) rating.getNumberOfVotes();
    }

    public Rating findByBookId(Long bookId) {
        return ratingRepository.findByBookId(bookId).orElse(createNewRating());
    }

    public Rating createNewRating() {
        Rating rating = new Rating();
        rating.setSumOfVotes(0L);
        rating.setNumberOfVotes(0L);
        return rating;
    }

}
