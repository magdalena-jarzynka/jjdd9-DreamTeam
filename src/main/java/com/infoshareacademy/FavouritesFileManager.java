package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class FavouritesFileManager {

    ObjectMapper mapper = new ObjectMapper();

    public List<Long> readFavourites() {

        try {
            return mapper.readValue(new File("Favourites.txt"), new TypeReference<List<Long>>() {
            });
        } catch (IOException e) {
            STDOUT.error("Nie udało się odczytać pliku! \n");
            return List.of();
        }
    }

    public void writeFavourites(List<Long> favourites) {
        try {
            mapper.writeValue(new File("Favourites.txt"), favourites);
        } catch (IOException e) {
            STDOUT.error("Nie udało się zapisać pliku! \n");
        }
    }
}
