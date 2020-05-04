package com.infoshareacademy.repository;

import com.infoshareacademy.FavouritesFileManager;

import java.util.List;

public class FavouritesRepository {

    private static FavouritesRepository favouritesRepository = null;
    private List<Long> favouriteBooks;
    private FavouritesFileManager favouritesFileManager;

    private FavouritesRepository() {
        favouritesFileManager = new FavouritesFileManager();
        favouriteBooks = favouritesFileManager.readFavourites();
    }


    public static FavouritesRepository getInstance() {
        if (favouritesRepository == null) {
            favouritesRepository = new FavouritesRepository();
        }
        return favouritesRepository;
    }

    public List<Long> getFavourites() {
        return favouriteBooks;
    }

    public void updateFavourites() {
        favouritesFileManager.writeFavourites(favouriteBooks);
    }

    public boolean add(Long bookId) {
        if (favouriteBooks.size() < 3) {
            favouriteBooks.add(bookId);
            updateFavourites();
            return true;
        }
        return false;
    }

    public void remove(Long bookId) {
        favouriteBooks.remove(bookId);
        updateFavourites();
    }

}