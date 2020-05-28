package com.infoshareacademy.dreamteam.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookParser {
    private static final Logger logger = LoggerFactory.getLogger(BookParser.class.getName());

    public List<BookPlain> readBookList(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, new TypeReference<List<BookPlain>>() {
            });
        } catch (IOException e) {
            logger.error("Can't read file! \n");
            return List.of();
        }
    }

}
