package Model;

import View.Player;

import java.util.List;

public class Unit extends Entity { //TODO tick()
    private int damage;
    private int range;

    private List<Tile> path;
    //TODO create an action timer (either in sync with server or separate)

    public Unit(Player owner, String name, int maxHP, int damage, int range) {
        super(owner, "Units/"+name, maxHP);
        this.damage = damage;
        this.range = range;
    }

    public void tick() {

    }
    public void move() {}
    public void attack() {}
    public void reteliate() {}
}
