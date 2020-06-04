package com.infoshareacademy.dreamteam.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileParser {
    private static final Logger logger = LoggerFactory.getLogger(FileParser.class.getName());

    public BookPlain readBookList(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, new TypeReference<BookPlain>() {
            });
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
