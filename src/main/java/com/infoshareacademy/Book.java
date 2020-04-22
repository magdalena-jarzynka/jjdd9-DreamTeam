package com.infoshareacademy;

import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private String type;

    public Book(String title, String author, String type) {
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Book> getBook() {
        ArrayList<Book> books = new ArrayList<>();
        Book book1 = new Book("Kuba Guzik", "Jan1", "przygodowy");
        Book book2 = new Book("Tuba Guzik", "Jan2", "przygodowy");
        Book book3 = new Book("Juba Guzik", "Jan3", "przygodowy");
        Book book4 = new Book("Muba Guzik", "Jan4", "przygodowy");
        Book book5 = new Book("Guba Guzik", "Jan5", "przygodowy");
        Book book6 = new Book("Juba Guzik", "Jan6", "przygodowy");
        Book book7 = new Book("Auba Guzik", "Jan7", "przygodowy");
        Book book8 = new Book("Quba Guzik", "Jan8", "przygodowy");
        Book book9 = new Book("Wuba Guzik", "Jan9", "przygodowy");
        Book book10 = new Book("Euba Guzik", "Jan10", "przygodowy");
        Book book11 = new Book("Ruba Guzik", "Jan11", "przygodowy");
        Book book12 = new Book("Tuba Guzik", "Jan12", "przygodowy");
        Book book13 = new Book("Yuba Guzik", "Jan13", "przygodowy");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        books.add(book11);
        books.add(book12);
        books.add(book13);
        return books;
    }
}