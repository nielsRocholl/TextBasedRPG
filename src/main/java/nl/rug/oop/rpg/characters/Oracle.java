package nl.rug.oop.rpg.characters;

import nl.rug.oop.rpg.player.Player;

/**
 * Definition oracle: An oracle can forsee the future and give valuable information to the player if
 * the player interacts with the oracle.
 * Oracle class, contains all information for oracle characters, and extends the abstract class NPC.
 */
public class Oracle extends NPC {
    private static final long serialVersionUID = 4L;
    private String knowledge;


    public Oracle(String name, String description, String knowledge) {
        super(name, description);
        setKnowledge(knowledge);
    }

    public void setKnowledge(String knowledge) { this.knowledge = knowledge; }

    /**
     * This method shows the option menu if a player interacts with an Oracle, and the oracle will print
     * its knowledge.
     * @param player is the main player.
     */
    @Override
    public void interact(Player player){
        System.out.println(getName() + " can foresee the future, she tells you the following:");
        System.out.println(knowledge);
        System.out.println(getName() + " has spoken, try to remember this knowledge and move on.");
    }
}
