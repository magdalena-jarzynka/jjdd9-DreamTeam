package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.object.Epoch;

import java.util.List;

public class EpochService {

    public String getEpochs(Book book) {
        List<Epoch> epochs = book.getEpochs();
        return String.join(", ", epochs.get(0).getName());
    }
}
