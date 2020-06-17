package com.infoshareacademy.dreamteam.service;

import org.apache.commons.lang3.math.NumberUtils;

public class ValidationService {

    public static Boolean validate(String parameter) {
        return NumberUtils.isCreatable(parameter);
    }
}
