package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.FragmentData;

import java.util.Optional;

public class FragmentService {

    public String getFragment(Book book) {
        return Optional.ofNullable(book)
                .map(Book::getBookFragment)
                .map(FragmentData::getFragment)
                .filter(fragment -> ((String) fragment).length() > 0)
                .orElse(" - ");
    }
}
