package com.infoshareacademy.menu.item;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import com.infoshareacademy.repository.FavouritesRepository;

import java.util.List;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class FavouritesMenu {
    private UserInputService userInputService = new UserInputService();

    public void chooseFavouritesMenu() {
        List<Long> favouritesId = FavouritesRepository.getInstance().getFavourites();
        do {
            cleanTerminal();
            STDOUT.info("ULUBIONE \n\n");
            STDOUT.info("W tej sekcji widoczne są pozycje dodane do Ulubionych. \n\n");

            for (Long id : favouritesId) {
                Book book = BookRepository.getInstance().getBooks().get(id);
                STDOUT.info("{}.Tytuł: {}{} \n {} Autor: {}{} \n {} ID: {}{}{} \n\n",
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
                    return;
                default:
                    STDOUT.info("Podaj odpowiednią cyfrę");
            }
        } while (true);
    }

    public void add(long id) {
        if (BookRepository.getInstance().getBooks().containsKey(id)) {
            if (FavouritesRepository.getInstance().add(id)) {
                STDOUT.info("\n \"{}\" dodano do ulubionych\n", BookRepository.getInstance().getBooks().get(id).getTitle());
            } else {
                STDOUT.info("\nNie można dodać więcej pozycji do ulubionych\n");
            }
        } else {
            STDOUT.info("\nPozycja o podanym ID nie istnieje\n");
        }
    }

    public void remove(long id) {
        if (FavouritesRepository.getInstance().getFavourites().contains(id)) {
            FavouritesRepository.getInstance().remove(id);
            STDOUT.info("\n \"{}\" usunięto z Ulubionych\n", BookRepository.getInstance().getBooks().get(id).getTitle());
        } else {
            STDOUT.info("\nPozycja o podanym ID nie znajduje się w Ulubionych\n");
        }
    }

    public void printAction(long id) {
        if (FavouritesRepository.getInstance().getFavourites().contains(id)) {
            STDOUT.info("\n Wybierz 1, aby usunąć pozycję z ulubionych.");
        } else {
            STDOUT.info("\n Wybierz 1, aby dodać pozycję do ulubionych.");
        }
    }

    public void performAction(long id) {
        if (FavouritesRepository.getInstance().getFavourites().contains(id)) {
            remove(id);
        } else {
            add(id);
        }
    }

}
