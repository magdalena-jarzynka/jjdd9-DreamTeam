package com.infoshareacademy.dreamteam.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class URLParser {
    private static final Logger logger = LoggerFactory.getLogger(URLParser.class.getName());

    public BookPlain readBook(URL url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BookPlain book;
        try {
            book = mapper.readValue(url, new TypeReference<BookPlain>() {
            });
            return book;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}