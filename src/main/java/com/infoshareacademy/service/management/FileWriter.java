package com.infoshareacademy.service.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.object.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;

public class FileWriter {

    ObjectMapper mapper = new ObjectMapper();

    public void writeToFile(List<Book> books) {
        try {
            mapper.writeValue(new File("BookList.txt"), books);
        } catch (IOException e) {
            STDOUT.error("Nie udało się zapisać pliku! \n");
        }
    }

}
