package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.room.Object;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;
import nl.rug.oop.rpg.interfaces.Interactable;

import java.io.Serializable;

/**
 * Class containing the Door constructor and other important Door related methods
 */
public abstract class Door extends Object implements Interactable, Serializable {
    //BehindDoor is the room which is behind the door (used when going through a door)
    private Room behindDoor;
    private static final long serialVersionUID = 6L;

    public Door(String description, Room room1){
        setDescription(description);
        setBehindDoor(room1);
    }

    public void setBehindDoor(Room room1){ behindDoor = room1; }

    public Room getBehindDoor(){ return behindDoor; }

    public void printDoorInfo(){ System.out.println("You go through the door"); }

    /**
     * Moves player from one room to another
     * @param player the main player
     */
    @Override
    public void interact(Player player){ player.setCurrentRoom(behindDoor); }
}