package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.room.Room;

/**
 * Normal door, can be used at any time.
 */
public class NormalDoor extends Door {
    private static final long serialVersionUID = 9L;

    public NormalDoor(String description, Room room1) {
        super(description, room1);
    }
}
