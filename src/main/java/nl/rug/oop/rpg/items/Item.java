package nl.rug.oop.rpg.items;

import nl.rug.oop.rpg.interfaces.Inspectable;

import java.io.Serializable;

/**
 * Items are things that traders and the player can posses. They are characterized by
 * health, damage and value. So they can increase a players health or do extra damage
 * if player has them in inventory.
 */
public class Item implements Inspectable, Serializable {
    private static final long serialVersionUID = 12L;
    private int health;
    private int damage;
    private int value;
    private String description;
    private String name;

    public Item(String name, String description, int value, int health, int damage){
        setDescription(description);
        setName(name);
        setHealth(health);
        setDamage(damage);
        setValue(value);

    }

    public void setDescription(String description) {
        if (description == null){
            throw new IllegalArgumentException("You did not enter a valid string for the description");
        }else {
            this.description = description;
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return the description of the item
     */
    @Override
    public String inspect() {
        return description;
    }
}
