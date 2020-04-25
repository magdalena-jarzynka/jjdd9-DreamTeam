package com.infoshareacademy.action;

import com.infoshareacademy.comparator.compareByAuthor;
import com.infoshareacademy.comparator.compareByTitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingOptions {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private final Scanner scanner = new Scanner(System.in);
    private String sortingOrder;
    private String sortingBy;

    public SortingOptions() {
    }

    public void run() {
        STDOUT.info("\n\n Wprowadź 1 aby sortować książki po tytule lub 2 aby sortować je po autorze. \n\n");
        String line = scanner.nextLine();
        if(Integer.parseInt(line) == 1) {
            sortingBy = "TITLE";
        } else if (Integer.parseInt(line) == 2) {
            sortingBy = "AUTHOR";
        }
        STDOUT.info("\n\n Wprowadź 1 aby wybrać porządek rosnący lub 2 aby wybrać porządek malejący dla sortowania wyników. \n\n");
        line = scanner.nextLine();
        if(Integer.parseInt(line) == 1) {
            sortingOrder = "ASC";
        } else if (Integer.parseInt(line) == 2) {
            sortingOrder = "DESC";
        }
        Configurations.writeToProperties("sortingBy", sortingBy);
        Configurations.writeToProperties("sortingOrder", sortingOrder);
    }

    public static List sortList(List list) {
        if (Configurations.getProperties().getProperty("sortingBy").equals("TITLE")) {
            if (Configurations.getProperties().getProperty("sortingOrder").equals("ASC")) {
                list.sort(new compareByTitle());
            } else if (Configurations.getProperties().getProperty("sortingOrder").equals("DESC")) {
                list.sort(Collections.reverseOrder(new compareByTitle()));
            }
        } else if (Configurations.getProperties().getProperty("sortingBy").equals("AUTHOR")) {
            if (Configurations.getProperties().getProperty("sortingOrder").equals("ASC")) {
                list.sort(new compareByAuthor());
            } else if (Configurations.getProperties().getProperty("sortingOrder").equals("DESC")) {
                list.sort(Collections.reverseOrder(new compareByAuthor()));
            }
        }
        return list;
    }

}
