package nl.rug.oop.rpg.player;

import nl.rug.oop.rpg.items.Item;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.interfaces.Tradeable;
import nl.rug.oop.rpg.room.Room;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The main player of the game, characterized by currentroom, health, damage he/she can do,
 * money and the inventory.
 */
public class Player implements Attackable, Tradeable, Serializable {
    private static final long serialVersionUID = 15L;
    private Room currentRoom;
    private int health; //IF HEALTH > 0, PLAYER == ALIVE
    private int damage; //damage a player does
    private int money;
    private final ArrayList<Item> items;



    public Player( Room room, int health, int damage, int money){
        setCurrentRoom(room);
        setHealth(health);
        setDamage(damage);
        setMoney(money);
        items = new ArrayList<>();
    }

    /**
     * Checks if the key to a door is in the inventory
     * @param key item that opens the door
     * @return boolean value
     */
    public boolean itemInInventory(Item key){
        for (Item item: items){
            if (item == key){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if player has enough money to buy an item
     * @param player main player
     * @param value items value
     * @return a boolean value to check if there is enough money.
     */
    public boolean checkBalance(Player player, int value){
        if (player.getMoney() - value < 0){
            System.out.println("You have insufficient funds, you are $"
                    + (player.getMoney() - value) + " short");
            return false;
        } else {
            player.setMoney(player.getMoney() - value);
            return true;
        }
    }

    /**
     * Used for combat with to enemy
     * @param health health level of payer
     * @param damage damage of enemy
     * @return health value
     */
    @Override
    public int attack(int health, int damage) {
        health -= damage;
        if (health < 0){
            System.out.println("Player died!");
        }
        return health;
    }

    /**
     * Adds item to inventory of player
     * @param item the item that need to be added
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
     * Print all items currently in inventory
     */
    public void showAllItems(){
        int cnt = 0;
        for (Item item : items) {
            System.out.println( "   (" + cnt + ") " + item.getName());
            cnt++;
        }
    }

    /**
     * some items increase the players damage, this function handles that.
     * @param item the purchased item
     */
    public void addItemPerks(Item item){ this.damage += item.getDamage(); }

    public Room getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    @Override
    public void setHealth(int health) { this.health = health; }

    @Override
    public int getHealth() { return health; }

    @Override
    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public int getDamage() { return damage; }

    @Override
    public void setMoney(int money){ this.money = money; }

    @Override
    public int getMoney(){ return money; }



}