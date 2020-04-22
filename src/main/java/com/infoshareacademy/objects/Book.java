package com.infoshareacademy.objects;

import java.util.List;

public class Book {
    String title;
    List<Author> authorList;
    List<Author> translatorList;
    Epoch epoch;
    List<Genre> genreList;
    Kind kind;
    String fragment;
    Boolean hasAudiobook;
    String isbnPdf;
}
