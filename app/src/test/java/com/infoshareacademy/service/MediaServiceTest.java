package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Media;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MediaServiceTest {

    @Test
    void addTwoAudiobooksToListOfMedia() {
        //GIVEN
        MediaService mediaService = new MediaService();
        Book book = new Book();
        List<Media> mediaList = new ArrayList<>();
        Media audiobook1 = new Media();
        Media audiobook2 = new Media();
        audiobook1.setName("Tytuł audiobooka1");
        audiobook2.setName("Tytuł audiobooka2");
        mediaList.add(audiobook1);
        mediaList.add(audiobook2);
        book.setMedia(mediaList);
        String result;

        //WHEN
        result = mediaService.getMedia(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Tytuł audiobooka1, Tytuł audiobooka2")
                .isNotEqualTo("Tytuł audiobooka1")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoEpochsInEpochsList() {
        //GIVEN
        MediaService mediaService = new MediaService();
        Book book = new Book();
        List<Media> mediaList = new ArrayList<>();
        book.setMedia(mediaList);
        String result;

        //WHEN
        result = mediaService.getMedia(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();
    }

}
