package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.FragmentData;

import java.util.Optional;

public class FragmentService {

    public String getFragment(Book book) {
        return Optional.ofNullable(book)
                .map(Book::getBookFragment)
                .map(FragmentData::getHtml)
                .filter(fragment -> !((String) fragment).isEmpty())
                .orElse("");
    }
}
