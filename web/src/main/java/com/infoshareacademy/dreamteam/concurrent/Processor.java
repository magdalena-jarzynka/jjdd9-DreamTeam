package com.infoshareacademy.dreamteam.concurrent;


import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.domain.pojo.BookDetailsPlain;
import com.infoshareacademy.dreamteam.domain.pojo.BookPlain;
import com.infoshareacademy.dreamteam.mapper.BookMapper;
import com.infoshareacademy.dreamteam.service.BookService;
import org.apache.http.client.HttpResponseException;

import java.util.List;

public class Processor implements Runnable {

    List<BookPlain> bookPlains;
    BookMapper bookMapper;
    BookService bookService;

    public Processor(List<BookPlain> subBooksPlain, BookMapper bM, BookService bS) {
        bookPlains = subBooksPlain;
        bookMapper = bM;
        bookService = bS;
    }

    public void run() {
        int i = 0;
        for (BookPlain bookPlain : bookPlains) {
            BookDetailsPlain bookDetailsPlain;
            i++;
            if (i > 25) {
                break;
            }

            try {
                bookDetailsPlain = bookService.parseBookDetailsFromApi(bookPlain.getHref());
            } catch (HttpResponseException e) {
                continue;
            }
            Book book = new Book();
            book = bookMapper.mapPlainToEntity(bookDetailsPlain);
            bookService.save(book);
        }
    }
}
