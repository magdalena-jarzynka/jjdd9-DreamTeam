package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

public class SortingByAuthor implements Comparator<Map.Entry<Long, Book>> {


    @Override
    public int compare(Map.Entry<Long, Book> book1, Map.Entry<Long, Book> book2) {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        Author author1 = book1.getValue().getAuthors().get(0);
        Author author2 = book2.getValue().getAuthors().get(0);
        int difference = c.compare(getLastName(author1), getLastName(author2));
        return difference != 0 ? difference :
                book1.getValue().getTitle().compareTo(book2.getValue().getTitle());

    }

    private String getLastName(Author author) {
        String[] partsOfName = author.getName().split(" ");
        return partsOfName[partsOfName.length - 1];
    }
}
