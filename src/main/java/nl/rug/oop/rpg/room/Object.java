package nl.rug.oop.rpg.room;

import nl.rug.oop.rpg.interfaces.Inspectable;

import java.io.Serializable;

/**
 * Abstract class used for setting up the description of NPCs, Rooms and Doors
 */
abstract public class Object  implements Inspectable, Serializable {
    private static final long serialVersionUID = 14L;
    protected String description;

    /**
      * @return the description
     */
    @Override
    public String inspect(){ return description; }

    public void setDescription(String description) {
        if (description == null){
            throw new IllegalArgumentException("You did not enter a valid string for the description");
        }else {
            this.description = description;
        }
    }
}