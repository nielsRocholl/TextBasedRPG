package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.Main;
import nl.rug.oop.rpg.characters.*;
import nl.rug.oop.rpg.doors.*;
import nl.rug.oop.rpg.io.Initializer;
import nl.rug.oop.rpg.io.SaveAndLoad;
import nl.rug.oop.rpg.io.Serializer;
import nl.rug.oop.rpg.items.Item;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;
import nl.rug.oop.rpg.suprise.Mp3Player;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;


public class Game implements Serializable {
    private static final long serialVersionUID = 13L;
    transient private final Scanner scanner = new Scanner(System.in);
    private Player niels;
    private Enemy arnold;
    private Trader hanSolo;
    private Trader ah;
    private Trader gimli;
    private Enemy smeagol;
    private Enemy palpatine;

    /**
     * Calls main game loop
     */
    public Game() {
    }

    /**
     * creates all the objects and connects important elements, like doors and rooms, or rooms and NPC's.
     */
    public void constructGameElements() {
        Room startingRoom   = new Room("You are currently not in a room, please enter a room.", false);
        Room endor          = new Room("You are on the forest moon of Endor, home of the Ewok species.", true);
        Room middleEarth    = new Room("You are in Middle Earth, walking through Mirkwood forrest.", true);
        Room c137           = new Room("You are in Rick Shanchez's garage, jerry is crying in the corner.", false);
        Room bernoulli      = new Room("You are in Bernoulli borg, home of the CS and AI students.",true);

        niels               = new Player(startingRoom, 40, 10 , 60);

        Item sword          = new Item("The sword of a thousand truths", "A very powerful sword", 50, 0, 35);
        Item key            = new Item("Key", "A key", 0, 0, 0);

        Door trapdoor1      = new TrapDoor("An old door, covered with cracks and dust.", startingRoom, 10);
        Door lockedDoor1    = new LockedDoor("A golden door, encrusted with diamonds.", bernoulli, key);
        Door door1          = new NormalDoor("A simple white door.", endor);
        Door supriseDoor1   = new SupriseDoor("You hear music behind this door", endor, "song2.wav", "rick.jpg");
        Door normalDoor2    = new NormalDoor("A flawless white door with elvish writing on it", middleEarth);
        Door normalDoor3    = new NormalDoor("A glass door, with on the other side the starting room.", startingRoom);
        Door supriseDoor2   = new SupriseDoor("A cold white door.", middleEarth, "song.wav", "ice.jpg");
        Door killAllDoor1   = new KillAllDoor("A door with written on it: \" Earth dimension C-137\"", c137);
        Door normalDoor4    = new NormalDoor("A space portal", endor);
        Door lockedDoor2    = new LockedDoor("A door with written on it \" The final boss awaits you here\"", bernoulli, key);
        Door trapDoor2      = new TrapDoor("A friendly looking door", c137, 20);
        Door trapDoor3      = new TrapDoor("A door with a small whole, through which you see mountains of gold", endor, 10);

        arnold              = new Enemy("Arnold Meister","The final boss Arnold Meister weilding a green laserpoter.", 150,25, true);
        palpatine           = new Enemy("Emperor Palpatine", "Emperor Palpatine getting of his imperial cruiser.", 30, 15, false);
        hanSolo             = new Trader("Han Solo", "Han solo is holding some interesting items.", 1000);
        NPC yoda            = new Oracle("Yoda", "The wise jedi master Yoda, meditating in the forrest",
                "If you poses the sword of a thousand truths you will be able to defeat the final boss with more ease!");
        NPC gandalf         = new Healer("Gandalf the Gray", "Gandalf the Gray, holding offering you a potion.", 40);
        NPC elrond          = new Oracle("Elrond", "Elrond, lord of Rivendell, might have important knowledge.",
                "The final boss may be found in Bernoulli Borg, which can be accessed through A golden door, encrusted with diamons.\n" +
                            "However this door can only be opened with a key!");
        smeagol             = new Enemy("Smeagol", "Smeagol lurking in the corner!", 20, 10, false);
        gimli               = new Trader("Gimli", "Gimli is holding some interesting items.", 1000);
        NPC rick            = new Healer("Rick Shanchez", "Rick Shanchez is working in his garage, he has healing potions.", 30);
        NPC mrPoopie        = new Oracle("Mr PoopieButhole", "The wise Mr PoopieButthole might have important knowledge.",
                "Open a door in the starting room with a key, use the sword to defeat the final boss.");
        NPC marco           = new Healer("Marco Wiering", "Marco Wiering is offering you his vape pen, which holds healing powers!", 20);
        ah                  = new Trader("AH to go", "The AH to go, you can buy important items here.", 1000);
        //Connect doors with roms
        startingRoom.addDoor(door1);
        startingRoom.addDoor(trapdoor1);
        startingRoom.addDoor(lockedDoor1);

        endor.addDoor(supriseDoor1);
        endor.addDoor(normalDoor2);
        endor.addDoor(normalDoor3);

        middleEarth.addDoor(supriseDoor2);
        middleEarth.addDoor(killAllDoor1);
        middleEarth.addDoor(normalDoor4);

        c137.addDoor(lockedDoor2);
        c137.addDoor(trapDoor2);
        c137.addDoor(normalDoor2);

        bernoulli.addDoor(normalDoor4);
        bernoulli.addDoor(trapDoor3);

        //Connect NPCs with rooms
        endor.addNPC(palpatine);
        endor.addNPC(hanSolo);
        endor.addNPC(yoda);

        middleEarth.addNPC(gandalf);
        middleEarth.addNPC(elrond);
        middleEarth.addNPC(smeagol);
        middleEarth.addNPC(gimli);

        c137.addNPC(rick);
        c137.addNPC(mrPoopie);

        bernoulli.addNPC(arnold);
        bernoulli.addNPC(marco);
        bernoulli.addNPC(ah);

        //connect items with npc : Give all traders all items to keep the game simple
        hanSolo.addItem(sword);
        hanSolo.addItem(key);
        gimli.addItem(sword);
        gimli.addItem(key);
        ah.addItem(sword);
        ah.addItem(key);
    }

    /**
     * Used to load the saved game
     */
    public void setSavedGame(Game game){
        niels       = game.niels;
        arnold      = game.arnold;
        hanSolo     = game.hanSolo;
        ah          = game.ah;
        gimli       = game.gimli;
        smeagol     = game.smeagol;
        palpatine   = game.palpatine;
    }

    /**
     *  This method stops the game if player is dead, or game is over.
     */
    public boolean killGame(Player a, Enemy enemy){
        if (a.getHealth() < 0){
            System.out.println("You died,GAME OVER!");
            System.exit(0);
        } else if (enemy.getHealth() <= 0){
            System.out.println("You defeated the final boss and won the game!");
            System.exit(0);
        }
        return true;
    }

    /**
     * A function to print slow, to create an old school gaming feel.
     * @param string The text we want to print
     * @param seconds the print delay
     */
    public void printSlow(String string, int seconds){
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            System.out.print(ch);
            try {
                Thread.sleep(seconds,0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print a welcome message and instructions
     */
    public void welcomeMessage() {
        String string = "Please enter your name: ";
        printSlow(string,60);
        String name = scanner.next();
        string = "Welcome " + name + ".\n"
                + "This game is a text based RPG, meaning that you will interact only through text based " +
                "input and output.\n" + "You win the game by defeating the main boss " + arnold.getName() + ".\n" +
                "If your health drops below 0, you die.\nHave Fun!\nThe game will start in:\n";
        printSlow(string,60);
        string = "5\n4\n3\n2\n1\n";
        new Mp3Player("count.wav");
        printSlow(string, 500);
        System.out.println("START\n");

    }

    /**
     * Checks validity of file name and send it to Serializer
     */
    public void checkFile() throws IOException {
        System.out.println("Please enter a file name (without the extension):");
        String name = scanner.next();
        File f = new File("savedgames" + File.separator + name + ".ser");
        if(f.createNewFile()){
            Serializer.safeGame(Main.getGame(),name);
        }else {
            System.out.println("This name already exists, try something else:");
            checkFile();
        }
    }

    public void loadProperties(boolean option) throws IOException {
        Initializer.createProperties("gameProp");
        if (option){
            Initializer.createProperties("gameProp");
        }else {
            Initializer.setDefault("gameProp");
        }
        Properties prop = Initializer.initGameFromProperties("gameProp");
        int pHealth = Integer.parseInt(prop.getProperty("playerHealth"));
        int pDamage = Integer.parseInt(prop.getProperty("playerDamage"));
        int fbHealth = Integer.parseInt(prop.getProperty("FinalBossDamage"));
        int fbDamage = Integer.parseInt(prop.getProperty("FinalBossHealth"));
        int money    = Integer.parseInt(prop.getProperty("Money"));
        niels.setHealth(pHealth);
        niels.setDamage(pDamage);
        arnold.setHealth(fbHealth);
        arnold.setDamage(fbDamage);
        niels.setMoney(money);
    }

    public void config() throws IOException {
        printSlow("You are about to start the game, what do you want to do?\n" +
                "   (0) Play Normally\n" +
                "   (1) Initialise from config\n" +
                "   (2) Set default config and load\n",60);
        int option = scanner.nextInt();
        switch (option){
            case 0:
                printSlow("You chose to play normally.\n\n", 60);
                break;
            case 1:
                loadProperties(true);
                printSlow("Load successful.\n\n", 60);
                break;

            case 2:
                loadProperties(false);
                printSlow("Set and load successful.\n\n", 60);
                break;
            default:
               printSlow("You entered an invalid option, try again: ",60);
                config();
        }
    }

    /**
     * Main game loop, interacts with player etc
     */
    public void playGame() throws IOException, ClassNotFoundException {
        constructGameElements();
        config();
        if (killGame(niels, arnold)){
            welcomeMessage();
        }
        while (killGame(niels, arnold)) {
            System.out.println("What do you want to do? \n" +
                    "   (0) Look around. \n" +
                    "   (1) Look for a way out. \n" +
                    "   (2) Look for company. \n" +
                    "   (3) QuickSafe.\n" +
                    "   (4) QuickLoad.\n" +
                    "   (5) Save.\n" +
                    "   (6) Load.");
            int option = scanner.nextInt();
            //Option 0 = look around (prints description of current room)
            switch (option){
                case 0:
                    System.out.println(niels.getCurrentRoom().inspect());
                    break;
                case 1:
                    //asks which door you want to take (-1 is stay here)
                    niels.getCurrentRoom().showAllDoors();
                    option = scanner.nextInt();
                    //Player goes through a door
                    //check if selected door exists
                    if (option > niels.getCurrentRoom().amountOfDoors() - 1 || option < -1) {
                        System.out.println("You entered an invalid option.");
                        //if player chooses to interact
                    }else if (option != -1) {
                        Door selected = niels.getCurrentRoom().selectDoor(option);
                        if (selected instanceof NormalDoor){
                            selected.printDoorInfo();
                        }
                        selected.interact(niels);
                        //Player stays in current room
                    }else {
                        System.out.println("You stay in this room.");
                    }
                    break;
                case 2:
                    //If no npc in room, npcInRoom will be false
                    boolean npcInRoom = niels.getCurrentRoom().showAllNPCs(); // IF NO ONE IN ROOM STOP THIS
                    //if npcInRoom is true, the player can interact with the NPC(s)
                    if (npcInRoom){
                        option = scanner.nextInt();
                        switch (option){
                            case 1: //Option 1 = interact
                                //Shows all names of NPCs and lets player choose with whom/what to interact
                                niels.getCurrentRoom().showNamesNPCs();
                                option = scanner.nextInt();
                                //Check if selected NPC/option exists
                                if (option < 0 || option > niels.getCurrentRoom().amountOfNPC() -1 ){
                                    System.out.println("You entered an invalid option.");
                                }else {
                                    NPC selected = niels.getCurrentRoom().selectNPC(option);
                                    selected.interact(niels);
                                }
                                break;
                            case 0: //(0) = do nothing)
                                System.out.println("You don't interact.");
                                break;
                            default:
                                System.out.println("You entered an invalid option.");
                        }
                    }
                    break;
                case 3:
                    Serializer.safeGame(Main.getGame(), "quickSave");
                    break;
                case 4:
                    Game savedGame;
                    try {
                        savedGame = Serializer.loadGame("quickSave.ser");
                        setSavedGame(savedGame);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    checkFile();
                    break;
                case 6:
                    setSavedGame(Objects.requireNonNull(SaveAndLoad.loadFromSaved()));
                    break;
                default:
                    System.out.println("You did not enter a valid option, try again!");

            }
        }
    }
}
