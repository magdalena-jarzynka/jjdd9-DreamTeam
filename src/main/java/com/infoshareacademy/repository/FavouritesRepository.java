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

}