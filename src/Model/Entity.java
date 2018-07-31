package Model;

import Utilities.Resources;
import View.Player;
import javafx.scene.image.Image;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private final transient Player owner;
    private String name;
    private int maxHP;
    private int currentHP;
    private String action;

    private Image image;

    public Entity(Player owner, String name, int maxHP) {
        this.owner = owner;
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.image = Resources.getImage("Tiles/"+name);
        action = "Idle";
    }

    public abstract void tick();
    public Image getImage() {
        return image;
    }
    public final Player getOwner() {
        return owner;
    }

}
