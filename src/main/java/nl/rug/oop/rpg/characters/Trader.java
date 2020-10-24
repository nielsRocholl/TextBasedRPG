package nl.rug.oop.rpg.characters;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Tradeable;
import nl.rug.oop.rpg.items.Item;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Definition Trader: A trader has an inventory filled with items, the player can purchase items if
 * he has the required funds available.
 * Trader class, contains all information for Trader characters, and extends the abstract class NPC,
 * and implements tradeable (interface with definitions for trading methods.
 */
public class Trader extends NPC implements Tradeable {
    private static final long serialVersionUID = 5L;
    private final ArrayList<Item> items;
    private int money;


    public Trader(String name, String description, int money) {
        super(name, description);
        setMoney(money);
        items = new ArrayList<>();
    }

    /**
     * Adds item to inventory i.e. list items
     * @param item the item that should be added.
     */
    @Override
    public void addItem(Item item){
        //check if the door is actually a real door
        if (item == null){
            throw new NullPointerException("Item is null");
        }else {
            items.add(item);
        }
    }

    /**
     * This method loops though the items list and print their description one by one.
     */
    public void showAllItems(){
        int cnt = 0;
        if (items.size() == 0){
            System.out.println("No inventory");
        }else{
            System.out.println(getName() + " has the following items:");
            for (Item item : items) {
                System.out.println("(" + cnt + ") " + item.inspect());
                cnt++;
            }
            System.out.println("Which item would you like to buy? (-1 : buy nothing)");
        }
    }

    /**
     * Loops through the items list, if it hits the item which the player wants to buy (itemNum)
     * We remove this item from the list and return it so that it can be added to the players
     * item list.
     * @param itemNum the index at which the chosen item is located in list items.
     */
    public Item buy(int itemNum) {
        int cnt = 0;
        for (Item item : items){
            if(itemNum == cnt){
                items.remove(item);
                return item;
            }
            cnt++;
        }
        throw new IllegalStateException("Item in list items is not found");
    }

    /**
     * This method shows the option menu if a player interacts with a Trader, and does shows all
     * information necessary for a transaction.
     * @param player @param player is the main player.
     */
    @Override
    public void interact(Player player){
        Scanner scanner = new Scanner(System.in);

        System.out.println(getName()
                + " has interesting items in his inventory, what would you like to do?\n"
                + "(0) Nothing.\n" + "(1) See inventory.");
        int option = scanner.nextInt();

        if (option == 0){
            System.out.println("You leave " + getName() + " alone and move on.");
        }else if (option == 1){
            showAllItems();
            option = scanner.nextInt();
            if (option == -1) {
                System.out.println("You buy nothing and move on.");
            }else if (option <= items.size() && option >= 0 ){
                Item bought = buy(option);
                player.checkBalance(player, bought.getValue());
                System.out.println("You bought " + bought.getName());
                player.addItem(bought);
                System.out.println("You now own the following items:");
                player.showAllItems();
                System.out.println("Your new funds are: " + player.getMoney() + ".");
                player.addItemPerks(bought);
                if (bought.getDamage() > 0){
                    System.out.println("Your new damage = " + player.getDamage() + ".");
                }
            }else {
                System.out.println("You entered an invalid option.");
            }
        }else {
            System.out.println("You entered an invalid option.");
        }
    }

    @Override
    public void setMoney(int money){ this.money = money; }

    @Override
    public int getMoney(){ return money; }

}
