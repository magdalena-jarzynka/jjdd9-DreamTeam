package com.infoshareacademy.dreamteam.service;

import org.apache.commons.lang3.math.NumberUtils;

public class ValidationService {

    public static Boolean validateIfParsableToLong(String parameter) {
        return NumberUtils.isCreatable(parameter);
    }
}
