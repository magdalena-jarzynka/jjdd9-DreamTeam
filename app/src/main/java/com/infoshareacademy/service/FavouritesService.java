package com.infoshareacademy.service;

import com.infoshareacademy.repository.FavouritesRepository;

import java.util.List;

public class FavouritesService {

    public List<Long> getFavourites() {
        return FavouritesRepository.getInstance().getFavourites();
    }

    public boolean add(Long bookId) {
        if (getFavourites().size() < 3 && !getFavourites().contains(bookId)) {
            getFavourites().add(bookId);
            FavouritesRepository.getInstance().updateFavourites();
            return true;
        }
        return false;
    }

    public void remove(Long bookId) {
        getFavourites().remove(bookId);
        FavouritesRepository.getInstance().updateFavourites();
    }
}
