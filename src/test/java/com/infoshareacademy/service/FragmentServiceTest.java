package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.FragmentData;
import org.junit.jupiter.api.Test;

class FragmentServiceTest {

    @Test
    void checkIfBookFragmentIsSameAsSet() {
        //GIVEN
        FragmentService fragmentService = new FragmentService();
        Book book = new Book();
        FragmentData fragment = new FragmentData();
        fragment.setHtml("This is my book fragment.");
        book.setBookFragment(fragment);
        String result;

        //WHEN
        result = fragmentService.getFragment(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("This is my book fragment.")
                .isNotEqualTo("This is my book fragment")
                .isNotEmpty();
    }

}
