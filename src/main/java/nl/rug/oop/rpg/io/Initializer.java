package nl.rug.oop.rpg.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Initializer {

    public static void createProperties(String fileName){
        File configDirectory = new File("config");
        configDirectory.mkdir();

        Properties gameProperties = new Properties();
        gameProperties.setProperty("playerHealth", "50");
        gameProperties.setProperty("playerDamage", "25");
        gameProperties.setProperty("Money", "50");
        gameProperties.setProperty("FinalBossHealth", "100");
        gameProperties.setProperty("FinalBossDamage", "20");

        try (FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName + ".ser")){
            gameProperties.store(fileWriter, "These are the properties of the game.");
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
   }

    public static Properties initGameFromProperties(String fileName) throws IOException {
        File configDirectory = new File("config");

        try (FileReader fileReader = new FileReader(configDirectory + File.separator + fileName + ".ser")){
            Properties gameProperties = new Properties();
            gameProperties.load(fileReader);
            return gameProperties;
        }
    }

    public static void setDefault(String fileName){
        Scanner scanner = new Scanner(System.in);
        String ans;
        File configDirectory = new File("config");
        configDirectory.mkdir();


        Properties gameProperties = new Properties();
        System.out.print("Set your health level: ");
        ans = scanner.next();
        gameProperties.setProperty("playerHealth", ans);
        System.out.print("Set your damage level: ");
        ans = scanner.next();
        gameProperties.setProperty("playerDamage", ans);
        System.out.print("Set your money: ");
        ans = scanner.next();
        gameProperties.setProperty("Money", ans);
        System.out.print("Set the final boss's health level: ");
        ans = scanner.next();
        gameProperties.setProperty("FinalBossHealth", ans);
        System.out.print("Set the final boss's Damage level: ");
        ans = scanner.next();
        gameProperties.setProperty("FinalBossDamage", ans);

        try (FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName + ".ser")){
            gameProperties.store(fileWriter, "These are the properties of the game.");
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }
}
