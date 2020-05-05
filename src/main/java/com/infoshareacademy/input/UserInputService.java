package com.infoshareacademy.input;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.WRONG_NUMBER;

public class UserInputService {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserInput() {
        int input;
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        if (input < 0) {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        return input;
    }
}
