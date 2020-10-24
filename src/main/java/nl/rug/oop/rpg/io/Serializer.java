package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.game.Game;

import java.io.*;

/**
 * Class to safe and load the game
 */
public class Serializer {

    /**
     * Saves a game
     * @param game The game that should be saved
     * @param fileName  the name we want our file to be called
     */
    public static void safeGame(Game game, String fileName) {
        /* will create a File object that refers to the location "savedgames" in the root of your project folder */
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir(); //NOT SURE IF THIS IS THE RIGHT WAY TO IMPLEMENT

        try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(game);
            System.out.println("Save Successful.");
        } catch (FileNotFoundException e){
            System.out.println("File could not be found.");
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }

    /**
     * Loads a game
     * @param fileName the name of the file our game data is stored in
     * @return returns the saved game
     * @throws IOException some input output exceptions that need to be handled
     * @throws ClassNotFoundException   some other exceptions
     */
    public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("savedgames");

        try (FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName);
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream) ){
            System.out.println("Load successful.");
            return (Game) objectInputStream.readObject();
        }
    }
}
