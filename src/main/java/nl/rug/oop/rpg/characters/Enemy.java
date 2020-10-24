package nl.rug.oop.rpg.characters;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.player.Player;

import java.util.Scanner;

/**
 * Definition of enemy: An enemy can be attacked, and will attack back if it is attacked.
 * Enemy class, contains all information for enemy characters, and extends the abstract class NPC.
 */
public class Enemy extends NPC implements Attackable {
    private static final long serialVersionUID = 1L;
    private int health;   //IF HEALTH > 0 NPC IS ALIVE
    private int damage;    //damage a player does
    boolean finalBoss;

    public Enemy(String name, String description, int health, int damage, boolean a) {
        super(name, description);
        setHealth(health);
        setDamage(damage);
        setFinalBoss(a);
    }

    @Override
    public int attack(int health, int damage) {
        health -= damage;
        if (health < 0){
            System.out.println("Player died!");
        }
        return health;
    }

    /**
     * This method shows the option menu if a player wants to interact with an enemy, and it
     * changes the health levels correspondingly.
     * @param player is the main player.
     */
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);

        //Now interact will go to the npcs class and interact will the show the interact options and execute them
        System.out.println("What would you like to do?\n" +
                "   (0) Nothing\n" +
                "   (1) Attack!");
        int option = scanner.nextInt();
        //Player attacks, Enemy attacks back.
        if (option == 0) {
            System.out.println("You leave " + getName() + " alone and move on.");
        }else if (option == 1){
            while (player.getHealth() > 0 && getHealth() > 0 && option == 1) {
                setHealth(attack(getHealth(), player.getDamage()));   //Player attacks
                System.out.println("You attacked " + getName() + "\n" + getName() +
                        "'s new health level: " + getHealth() + ".");
                if (getHealth() > 0){
                    player.setHealth(player.attack(player.getHealth(), getDamage()));         //NPC Attacks
                    System.out.println(getName() + " Attacks back.\n" +
                            "Your new health level: " + player.getHealth());
                    if (getHealth() > 0 && player.getHealth() > 0){
                        System.out.println("Attack again?\n" +
                                "   (0) No.\n" +
                                "   (1) Yes.");
                        option = scanner.nextInt();
                    }
                }
            }
            if (getHealth()<= 0) {
                if (finalBoss) {
                    System.out.print("Arnold Meister is almost defeated, before he can die you need to solve his puzzle:\n" +
                            "What is the sum of the first three prime numbers: ");
                    while (option != 10) {
                        option = scanner.nextInt();
                        if (option == 10) {
                            System.out.println("Congratulations, you passed the test!");
                        } else {
                            System.out.print("Wrong! try again: ");
                        }
                    }
                }else {
                    System.out.println("You defeated " + getName() + "!" );
                    player.getCurrentRoom().setBool(false);
                }
            }
        }else{
            System.out.println("You entered an invalid option, try again.");
            interact(player);
        }
    }

    public void setFinalBoss(boolean a){ finalBoss = a; }

    @Override
    public void setHealth(int health) { this.health = health; }

    @Override
    public int getHealth() { return health; }

    @Override
    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public int getDamage() { return damage; }
}
