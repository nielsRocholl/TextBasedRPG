package nl.rug.oop.rpg.doors;

import nl.rug.oop.rpg.suprise.ImageDemo;
import nl.rug.oop.rpg.suprise.Mp3Player;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.room.Room;

/**
 * Just try these doors (~˘▾˘)~
 */
public class SupriseDoor extends Door{
    private static final long serialVersionUID = 10L;
    private String songPath;
    private String picPath;
    //Room1 should lead back to the same room
    public SupriseDoor(String description, Room room1, String songPath, String picPath) {
        super(description, room1);
        setSongPath(songPath);
        setPicPath(picPath);
    }

    /**
     * sets path to song file
     * @param songPath path to file
     */
    public void setSongPath(String songPath){ this.songPath = songPath; }

    /**
     * sets path to picture
     * @param picPath path to song
     */
    public void setPicPath(String picPath){ this.picPath = picPath; }

    /**
     * interacts by opening the file and song and does damage
     * @param player the main player
     */
    public void interact(Player player){
        new ImageDemo(picPath);
        new Mp3Player(songPath);
        player.setCurrentRoom(getBehindDoor());

        System.out.println("you have been fooled, and sustained 10 points health damage.");
        player.setHealth(player.getHealth()-10);
        System.out.println("Your new health level is: " + player.getHealth() + ".");
    }
}
