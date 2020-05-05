package com.infoshareacademy;

import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.service.management.BookManagement;

/**
 * DreamTeam
 */
public class Main {

    public static void main(String[] args) {
      //  Menu menu = new Menu();
   //     menu.run();
        BookManagement bookManagement = new BookManagement();
        bookManagement.run();
    }
}
