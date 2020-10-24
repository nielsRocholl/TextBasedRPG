package nl.rug.oop.rpg.characters;

import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;

import java.io.Serializable;

/**
 * The abstract class NPC is used by all characters, the most important pieces of
 * this class are the description and name. This class implements the interface
 * inspectable and interactable, since want to to be able to inspect (get description) and
 * interact with the characters.
 */
public abstract class NPC implements Inspectable, Interactable, Serializable {
    private static final long serialVersionUID = 3L;
    private String description;
    private String name;


    public NPC(String name, String description){
        setName(name);
        setDescription(description);
    }

    public void setDescription(String description) {
        if (description == null){
            throw new IllegalArgumentException("You did not enter a valid string for the description");
        }else {
            this.description = description;
        }
    }

    public void setName(String name){ this.name = name; }

    public String getName(){ return name; }

    /**
     * Returns the description
     */
    @Override
    public String inspect(){ return description; }


}
