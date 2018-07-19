package Model;

import Utilities.Resources;
import View.Player;
import javafx.scene.image.Image;

public abstract class Entity {
    private final Player owner;
    private String name;
    private int maxHP;
    private int currentHP;

    private Image image;

    public Entity(Player owner, String name, int maxHP) {
        this.owner = owner;
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.image = Resources.getImage("Tiles/"+name);
    }

    public Image getImage() {
        return image;
    }
    public final Player getOwner() {
        return owner;
    }

}
