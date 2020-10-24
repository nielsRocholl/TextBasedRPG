package nl.rug.oop.rpg.room;

import nl.rug.oop.rpg.characters.NPC;
import nl.rug.oop.rpg.doors.Door;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing room constructor and other important methods
 */
public class Room extends Object {
    private static final long serialVersionUID = 16L;
    private boolean bool;
    //List containing all doors in a room
    private final ArrayList<Door> doors;
    //List containing all NPCs in a room
    private final ArrayList<NPC> npc;


    public Room(String description, boolean bool){
        setDescription(description);
        setBool(bool);
        doors = new ArrayList<>();
        npc = new ArrayList<>();
    }

    /**
     * a method for adding doors to the list
     */
    public void addDoor(Door door){
        //check if the door is actually a real door
        if (door == null){
            throw new NullPointerException("Door is null");
        }else {
            doors.add(door);
        }
    }

    /**
     * dds NPC to list
     * @param npc the character that should be added to the room
     */
    public void addNPC(NPC npc){
        //Checks if NPC is a real npc
        if (npc == null){
            throw new NullPointerException("NPC is null");
        }else {
            this.npc.add(npc);
        }
    }

    /**
     * iterates through all the visible doors in the room and prints description
     */
    public void showAllDoors(){
        int cnt = 0;
        System.out.println("You look around for doors.\n" + "You see:");
        for (Door item : doors) {
            System.out.println( "(" + cnt + ") " + item.description);
            cnt++;
        }
        System.out.println("Which door do you take? (-1 : stay here)");
    }

    /**
     * checks the validity of the integer which is passed to selectDoor
     * @param val entered value
     * @return 0
     */
    public int checkValue(int val){
        Scanner scanner = new Scanner(System.in);
        if (val <= doors.size() && val >= 0){
            return val;
        }else {
            System.out.println("You look around for doors.\n" + "You see:");
            val = scanner.nextInt();
            checkValue(val);
        }
        return 0;
    }

    /**
     * Returns the selected door
     * @param ans the option of the player
     * @return the selected door.
     */
    public Door selectDoor(int ans){
        int cnt = 0;
        ans = checkValue(ans);
        for (Door item : doors){
            if(ans == cnt){
                return item;
            }
            cnt++;
        }
        return null;
    }

    /**
     * Shows all NPCs in the room
     * @return boolean
     */
    public boolean showAllNPCs(){
        int cnt = 0;
        //If no ones in the room
        if (npc.size() == 0){
            System.out.println("There's no one in the room");
            return false;
        } else {
            System.out.println("You look if there's someone here.\n" + "You see:");
            for (NPC item : npc) {
                System.out.println("(" + cnt + ") " + item.inspect());
                cnt++;
            }
            System.out.println("Do you want to interact with one of these characters?\n" + "(0) No\n" + "(1) Yes");
            return true;
        }
    }

    /**
     * Shows all names of the npc, used fo selected who you want to interact with
     */
    public void showNamesNPCs(){
        int cnt = 0;
        System.out.println("With which character do you want to interact?");
        for (NPC item : npc) {
            System.out.println( "(" + cnt + ") " + item.getName());
            cnt++;
        }
    }

    /**
     * Returns the selected NPC
     * @param ans the selection of th player
     * @return the NPC
     */
    public NPC selectNPC(int ans){
        int cnt = 0;
        //make a check for NPC's like in doors
        for (NPC item : npc){
            if(ans == cnt){
                return item;
            }
            cnt++;
        }
        return null;
    }

    /**
     * A check to see if there are enemies in the room, used for locked doors.
     * @return boolean
     */
    public boolean enemyInRoom() { return bool; }

    public void setBool(boolean bool){ this.bool = bool; }

    public int amountOfDoors(){ return doors.size(); }

    public int amountOfNPC(){ return npc.size(); }

}