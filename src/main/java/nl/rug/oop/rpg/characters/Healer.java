package nl.rug.oop.rpg.characters;

import nl.rug.oop.rpg.player.Player;

import java.util.Scanner;

/**
 * Definition healer: A healer can give health points to the player.
 * Healer class, contains all information for healer characters, and extends the abstract class NPC.
 */
public class Healer extends NPC {
    private static final long serialVersionUID = 2L;
    private int healingPower;
    public Healer(String name, String description, int healingPower){
        super(name,description);
        setHealingPower(healingPower);
    }

    public int getHealingPower(){ return healingPower; }

    /**
     * @param healingPower is the amount of health points which is added to the players health level
     *                     if the player chooses to interact with the healer.
     */
    public void setHealingPower(int healingPower){ this.healingPower =  healingPower;
    }

    /**
     * This method shows the option menu if a player interacts with a Healer, and changes the players
     * health level correspondingly.
     * @param player @param player is the main player.
     */
    @Override
    public void interact(Player player){
        Scanner scanner = new Scanner(System.in);

        System.out.println(getName()
                + " offers you a potion, what would you like to do?\n"+
                "   (0) Nothing.\n" +
                "   (1) Drink it.");
        int option = scanner.nextInt();
        if (option == 0){
            System.out.println("You leave " + getName() + " alone and move on.");
        }else if (option == 1){
            player.setHealth(player.getHealth() + getHealingPower());
            System.out.println("You drink the potion, your health increases with +"
                    + getHealingPower()
                    + "\n" + "Your new health level is: " + player.getHealth() + ".");
        }
    }
}
