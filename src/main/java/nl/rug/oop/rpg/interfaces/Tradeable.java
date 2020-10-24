package nl.rug.oop.rpg.interfaces;

import nl.rug.oop.rpg.items.Item;

public interface Tradeable {

    void addItem(Item item);

    void setMoney(int money);

    int getMoney();
    }
