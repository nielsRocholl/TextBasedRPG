package nl.rug.oop.rpg.interfaces;

//An interface with containing methods which are used during combat
public interface Attackable {
    //All getters and setters are self explanatory
    void setHealth(int health);

    int getHealth();

    void setDamage(int damage);

    int getDamage();
    //Returns health level after attack
    int attack(int health, int damage);

}
