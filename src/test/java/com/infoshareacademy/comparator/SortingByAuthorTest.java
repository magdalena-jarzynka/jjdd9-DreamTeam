package com.infoshareacademy.comparator;

import com.infoshareacademy.object.Author;
import com.infoshareacademy.object.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SortingByAuthorTest {

    @Test
    public void getAuthorSecondName() {
        //GIVEN
        Author author = new Author();
        author.setName("Michal Aniol");
        String[] partsOfName = author.getName().split(" ");
        String result;

        //WHEN
        result = partsOfName[partsOfName.length - 1];

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Aniol")
                .isNotEqualTo("Michal")
                .isNotBlank();
    }


    @Test
    void compareTwoBooksWithDifferentAuthors() {
        //GIVEN
        SortingByAuthor sortingByAuthor = new SortingByAuthor();
        Book book1 = new Book();
        Book book2 = new Book();
        Map.Entry<Long, Book> bookMap1 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 1L;
            }

            @Override
            public Book getValue() {
                return book1;
            }

            @Override
            public Book setValue(Book book) {
                return book = book1;
            }
        };

        Map.Entry<Long, Book> bookMap2 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 2L;
            }

            @Override
            public Book getValue() {
                return book2;
            }

            @Override
            public Book setValue(Book book) {
                return book = book2;
            }
        };
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setName("Jan Kowalski");
        author2.setName("Zbigniew Longinus");
        List<Author> authors1 = new ArrayList<>();
        List<Author> authors2 = new ArrayList<>();
        authors1.add(author1);
        authors2.add(author2);
        bookMap1.getValue().setAuthors(authors1);
        bookMap2.getValue().setAuthors(authors2);
        int result;

        //WHEN
        result = sortingByAuthor.compare(bookMap1, bookMap2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1)
                .isNotEqualTo(0);

    }

    @Test
    void compareTwoBooksFromTheSameAuthor() {
        //GIVEN
        SortingByAuthor sortingByAuthor = new SortingByAuthor();
        Book book1 = new Book();
        Book book2 = new Book();
        Map.Entry<Long, Book> bookMap1 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 1L;
            }

            @Override
            public Book getValue() {
                return book1;
            }

            @Override
            public Book setValue(Book book) {
                return book = book1;
            }
        };

        Map.Entry<Long, Book> bookMap2 = new Map.Entry<>() {
            @Override
            public Long getKey() {
                return 2L;
            }

            @Override
            public Book getValue() {
                return book2;
            }

            @Override
            public Book setValue(Book book) {
                return book = book2;
            }
        };
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setName("Jan Kowalski");
        author2.setName("Jan Kowalski");
        List<Author> authors1 = new ArrayList<>();
        List<Author> authors2 = new ArrayList<>();
        authors1.add(author1);
        authors2.add(author2);
        String title1 = "Chapter 1";
        String title2 = "Chapter 2";
        bookMap1.getValue().setAuthors(authors1);
        bookMap1.getValue().setTitle(title1);
        bookMap2.getValue().setAuthors(authors2);
        bookMap2.getValue().setTitle(title2);

        int result;

        //WHEN
        result = sortingByAuthor.compare(bookMap1, bookMap2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1)
                .isNotEqualTo(0);

    }
}
