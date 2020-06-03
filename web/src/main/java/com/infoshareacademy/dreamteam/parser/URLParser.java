package com.infoshareacademy.dreamteam.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLParser {
    private static final Logger logger = LoggerFactory.getLogger(URLParser.class.getName());

    public List<BookPlain> readBookList(URL url) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        List<BookPlain> listOfBooks= new ArrayList<>();
        try {
            listOfBooks.add(mapper.readValue(url, new TypeReference<BookPlain>() {}));
            return listOfBooks;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return List.of();
        }
    }


}
