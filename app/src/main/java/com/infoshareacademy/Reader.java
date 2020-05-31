package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Genre;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;


public class Reader {

    public List<Book> readBookList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("BookList.txt"), new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            STDOUT.error("Nie udało się odczytać pliku! \n");
            return List.of();
        }
    }

    public List<Genre> readGenreList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("genres.txt"), new TypeReference<List<Genre>>() {
            });
        } catch (IOException e) {
            STDOUT.error("Nie udało się odczytać pliku! \n", e);
            return List.of();
        }

    }
}
