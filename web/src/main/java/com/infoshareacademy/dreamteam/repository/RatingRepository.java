package com.infoshareacademy.dreamteam.repository;

import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.entity.Rating;

import java.util.Optional;

public interface RatingRepository {


    void update (Rating rating);

    Optional<Rating> findByBookId(Long bookId);
}
