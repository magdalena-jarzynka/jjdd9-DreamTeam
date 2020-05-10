package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Epoch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EpochServiceTest {

    @Test
    void addTwoEpochsToListOfEpochs() {

        //GIVEN
        EpochService epochService = new EpochService();
        Book book = new Book();
        List<Epoch> epochs = new ArrayList<>();
        Epoch epoch1 = new Epoch();
        Epoch epoch2 = new Epoch();
        epoch1.setName("Epoka lodowcowa");
        epoch2.setName("Inna epoka");
        epochs.add(epoch1);
        epochs.add(epoch2);
        book.setEpochs(epochs);
        String result;

        //WHEN
        result = epochService.getEpochs(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Epoka lodowcowa, Inna epoka")
                .isNotEqualTo("Inna epoka")
                .isNotEmpty();
    }

    @Test
    void checkIfEmptyWithNoEpochsInEpochsList() {
        //GIVEN
        EpochService epochService = new EpochService();
        Book book = new Book();
        List<Epoch> epochs = new ArrayList<>();
        book.setEpochs(epochs);
        String result;

        //WHEN
        result = epochService.getEpochs(book);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();
    }

}
