package com.infoshareacademy.service.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.object.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileWriter {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    ObjectMapper mapper = new ObjectMapper();

    public void writeToFile(List<Book> books) {
        try {
            mapper.writeValue(new File("BookList.txt"), books);
        } catch (IOException e) {
            STDOUT.error("Nie udało się zapisać pliku! \n");
        }
    }

    //public void writeToFile(Map<Long, Book> books) {
    //        try {
    //            mapper.writeValue(new File("BookList.txt"), books);
    //        } catch (IOException e) {
    //            STDOUT.error("Nie udało się zapisać pliku! \n");
    //        }
    //    }




}
