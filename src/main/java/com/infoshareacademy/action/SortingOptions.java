package com.infoshareacademy.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class SortingOptions {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private Scanner scanner = new Scanner(System.in);
    private int sortingOrder;

    public SortingOptions() {
    }

    public void run() {
        STDOUT.info("\n\n Wprowadź 1 aby wybrać porządek rosnący lub 2 aby wybrać porządek malejący dla sortowania wyników). \n\n");
        String line = scanner.nextLine();
        sortingOrder = Integer.parseInt(line);
        if (sortingOrder == 1) {
            Configurations.writeToProperties("sorting", "ASC");
        } else if (sortingOrder == 2) {
            Configurations.writeToProperties("sorting", "DESC");
        }
    }
}
