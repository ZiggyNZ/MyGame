package Model;

import View.Player;

public abstract class Entity {
    private final Player owner;
    private int health;

    public Entity(Player owner) {
        this.owner = owner;
    }

    public final Player getOwner() {
        return owner;
    }

}
