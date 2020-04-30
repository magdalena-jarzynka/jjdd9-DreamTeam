package com.infoshareacademy.service;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import com.sun.java.accessibility.util.Translator;

import java.util.List;

public class TranslatorService {

    private String getTranslators(Book book) {
        List<Author> translators = book.getTranslators();
        String translatorList = "";
        for (Author translator : translators) {
            translatorList = translatorList + translator.getName() + " , ";
        }
        return translatorList;
    }
}
