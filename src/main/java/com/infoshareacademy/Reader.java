package com.infoshareacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.object.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Reader {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public List<Book> readBookList() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("BookList.txt"), new TypeReference<List<Book>>() {
            });
        } catch (IOException e) {
            STDOUT.error("Nie znaleziono pliku! \n", e);
            return List.of();
        }

    }
}



