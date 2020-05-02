package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Kind;

import java.util.List;

public class KindService {

    public String getKinds (Book book) {
        List<Kind> kinds = book.getKinds();
        return String.join(", ", kinds.get(0).getName());
    }
}
