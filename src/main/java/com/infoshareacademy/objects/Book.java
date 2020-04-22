package com.infoshareacademy.objects;

import java.util.List;

public class Book {
    String title;
    List<Author> authors;
    List<Author> translators;
    Epoch epoch;
    List<Genre> genres;
    Kind kind;
    String fragment;
    Boolean hasAudiobook;
    String isbnPdf;
}
