package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.game.Game;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a class to select and load the saved games
 */
public class SaveAndLoad {

    /**
     * looks in the directory containing saved games, and sets game to the saved game the user wants to load.
     * @return the game
     * @throws IOException input output exception handeled
     * @throws ClassNotFoundException If we pass a wrong class
     */
    public static Game loadFromSaved() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        File dir = new File("savedgames" + File.separator);
        File[] directoryListing = dir.listFiles();
        if (Objects.requireNonNull(dir.listFiles()).length == 0){
            System.out.println("No saved games found.");
            return Main.getGame();
        }
        if (directoryListing != null) {
            int cnt = 0;
            System.out.println("You have the following saved games:");
            for (File child : directoryListing) {
                System.out.println("    (" + cnt + ") " + (child.getName()));
                cnt++;
            }
            System.out.println("Which saved game would you like to load? (-1 : none)");
            int option = scanner.nextInt();
            if (option > Objects.requireNonNull(dir.listFiles()).length || option < -1){
                System.out.println("You entered an invalid option, please try again: ");
                loadFromSaved();
            }
            cnt = 0;
            if (option == -1){
                return Main.getGame();
            }else {
                for (File child : directoryListing) {
                    if(cnt == option){
                        return Serializer.loadGame(child.getName());
                    }
                    cnt++;
                }
            }
        } else {
            System.out.println("Directory or file wrong");
        }
        return Main.getGame();

    }
}
