package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;
import nl.rug.oop.rpg.interfaces.Interactable;

/**
 * trap door leads back to the same room and does damage
 */
public class TrapDoor extends Door implements Interactable {
    private static final long serialVersionUID = 11L;
    private int damage;

    public TrapDoor(String description, Room room1, int damage) {
        super(description, room1);
        setDamage(damage);

    }

    public void setDamage(int damage) { this.damage = damage; }

    /**
     * players stays in same room and gets damage
     * @param player main player
     */
    @Override
    public void interact(Player player){
        player.setHealth(player.getHealth() - damage);
        System.out.println("This was a trap door, you sustained -"
                + damage + " points damage, and you did not leave the current room.");
        player.setCurrentRoom(getBehindDoor());
        System.out.println("Your new health level is: " + player.getHealth() + ".");
    }
}
