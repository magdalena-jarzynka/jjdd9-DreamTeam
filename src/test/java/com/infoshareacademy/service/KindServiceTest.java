package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Kind;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class KindServiceTest {

    @Test
    void addTwoKindsToListOfKinds() {
        //GIVEN
        KindService kindService = new KindService();
        Book book = new Book();
        List<Kind> kinds = new ArrayList<>();
        Kind kind1 = new Kind();
        Kind kind2 = new Kind();
        kind1.setName("Gatunek pierwszy");
        kind2.setName("Gatunek drugi");
        kinds.add(kind1);
        kinds.add(kind2);
        book.setKinds(kinds);
        String result;

        //WHEN
        result = kindService.getKinds(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Gatunek pierwszy, Gatunek drugi")
                .isNotEqualTo("Gatunek pierwszy")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoEpochsInEpochsList() {
        //GIVEN
        KindService kindService = new KindService();
        Book book = new Book();
        List<Kind> kinds = new ArrayList<>();
        book.setKinds(kinds);
        String result;

        //WHEN
        result = kindService.getKinds(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();
    }

}
