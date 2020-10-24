package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;
import nl.rug.oop.rpg.items.Item;

/**
 * Can only be opened if player defeats all enemies in the room
 */
public class LockedDoor extends Door {
    private static final long serialVersionUID = 8L;
    //this item needs to be in the players inventory
    private Item item;


    public LockedDoor(String description, Room room1, Item item) {
        super(description, room1);
        setItem(item);
    }

    /**
     * This is the item (key) which needs to be in the players inventory
     * @param item the item
     */
    public void setItem(Item item){ this.item = item; }

    /**
     * If player tries to pass without key, print message, if key is in inventory the
     * player can unlock the door and pass.
     * @param player the main player
     */
    @Override
    public void interact(Player player){
        if (player.itemInInventory(item)) {
            System.out.println("You poses the key for this door, you unlock it and enter the next room");
            player.setCurrentRoom(getBehindDoor());
        }else {
            System.out.println("This door is locked, you need a key to unlock it");
        }
    }



}
