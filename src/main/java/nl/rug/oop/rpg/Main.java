package nl.rug.oop.rpg;

import nl.rug.oop.rpg.game.Game;

import java.io.IOException;

/**
 * Main class initializes the game
 */
public class Main {
    private static Game RPG;
    /**
     * Only used for initializing the game
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RPG = new Game();
        RPG.playGame();
    }

    public static Game getGame(){ return RPG; }

}