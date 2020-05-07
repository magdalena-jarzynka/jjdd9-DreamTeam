package com.infoshareacademy.menu.item;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.Breadcrumbs;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.BookService;
import com.infoshareacademy.service.FavouritesService;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class FavouritesMenu {

    private UserInputService userInputService = new UserInputService();
    private FavouritesService favouritesService = new FavouritesService();
    private BookService bookService = new BookService();

    public void chooseFavouritesMenu() {
        do {
            cleanTerminal();
            STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
            STDOUT.info("ULUBIONE \n\n");
            STDOUT.info("W tej sekcji widoczne są pozycje dodane do Ulubionych. \n\n");

            for (Long id : favouritesService.getFavourites()) {
                Book book = bookService.findAllBooks().get(id);
                STDOUT.info("{}Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
                        ConsoleColors.BLACK_BOLD.getColorType(),
                        ConsoleColors.RED.getColorType(), book.getTitle(),
                        ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.BLUE.getColorType(), book.getAuthors().get(0).getName(),
                        ConsoleColors.BLACK_BOLD.getColorType(), ConsoleColors.YELLOW_BOLD.getColorType(), id,
                        ConsoleColors.RESET.getColorType());
            }
            STDOUT.info("Wybierz 1 i numer ID, aby usunąć pozycję z ulubionych.\n\n");
            STDOUT.info("Wybierz 0 aby wrócić do głównego Menu. \n");

            switch (userInputService.getUserInput()) {
                case 1:
                    remove(userInputService.getUserInput());
                    break;
                case 0:
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                default:
                    STDOUT.info("Podaj odpowiednią cyfrę");
            }
        } while (true);
    }

    public void add(long id) {
        if (bookService.findAllBooks().containsKey(id)) {
            if (favouritesService.add(id)) {
                STDOUT.info("\n \"{}\" dodano do ulubionych\n", bookService.findAllBooks().get(id).getTitle());
            } else {
                STDOUT.info("\nNie można dodać pozycji do ulubionych\n");
            }
        } else {
            STDOUT.info("\nPozycja o podanym ID nie istnieje\n");
        }
    }

    public void remove(long id) {
        if (favouritesService.getFavourites().contains(id)) {
            favouritesService.remove(id);
            STDOUT.info("\n \"{}\" usunięto z Ulubionych\n", bookService.findAllBooks().get(id).getTitle());
        } else {
            STDOUT.info("\nPozycja o podanym ID nie znajduje się w Ulubionych\n");
        }
    }

    public void printAction(long id) {
        if (favouritesService.getFavourites().contains(id)) {
            STDOUT.info("\n Wybierz 1, aby usunąć pozycję z ulubionych.");
        } else {
            STDOUT.info("\n Wybierz 1, aby dodać pozycję do ulubionych.");
        }
    }

    public void performAction(long id) {
        if (favouritesService.getFavourites().contains(id)) {
            remove(id);
        } else {
            add(id);
        }
    }

}
