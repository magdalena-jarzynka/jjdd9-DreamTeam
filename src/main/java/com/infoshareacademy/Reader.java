package com.infoshareacademy;


import com.google.gson.Gson;
import com.infoshareacademy.objects.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;


public class Reader {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public List<Book> readBookList () {

        Gson gson = new Gson();

        try {
            Book[] bookArray = gson.fromJson(new FileReader("src/main/resources/BookList.txt"), Book[].class);
            return Arrays.asList(bookArray);
        } catch (FileNotFoundException e) {
            STDOUT.error("Nie znaleziono pliku! \n");
            return null;
        }

    }




}
