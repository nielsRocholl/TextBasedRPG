package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;

/**
 * Can only be opened if player defeats all enemies in the room
 */

public class KillAllDoor extends Door {
    private static final long serialVersionUID = 7L;

    public KillAllDoor(String description, Room room1) {
        super(description, room1);
    }

    /**
     * Prints message if someone tries to pass without killing all enemies, if all are
     * dead, the player can pass.
     * @param player the main player.
     */
    public void interact(Player player){
        if (player.getCurrentRoom().enemyInRoom()){
            System.out.println("You have to kill all enemies in the room to open this door");
        }else {
            player.setCurrentRoom(getBehindDoor());
        }
    }
}
