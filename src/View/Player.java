package View;

import Model.Entity;
import Model.Map;
import Model.Tile;
import Utilities.Resources;

import java.util.ArrayList;
import java.util.List;

public class Player {
    final String userID;

    //All the player's resources
    private int wood = 0;
    private int stone = 0;
    private int metal = 0;
    private int oil = 0;
    private List<Entity> entities = new ArrayList<>();

    //Player's current instance
    private Map observing;
    private Tile selected;

    public Player(String userID) {
        this.userID = userID;
        this.observing = Resources.getMap("Island");
    }

    public boolean owns(Entity entity) {
        if(entity == null) {
            return false;
        }
        return entities.contains(entity);
    }
    public void inherit(Entity entity) {
        this.entities.add(entity);
    }
    public Tile getSelected() {
        return selected;
    }
    public void setSelected(Tile tile) {
        this.selected = tile;
    }
    public final String getUserID() {
        return userID;
    }
    public Map getObserving() {
        return observing;
    }
    public void setObserving(Map map) {
        this.observing = map;
    }

}
