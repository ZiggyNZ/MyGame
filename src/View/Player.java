package View;

import Model.Entity;
import Model.Map;

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
    private Model.Map observing;
    private Entity selected;

    public Player(String userID) {
        this.userID = userID;
    }

    public void inherit(Entity entity) {
        this.entities.add(entity);
    }
    public Entity getSelected() {
        return selected;
    }
    public final String getUserID() {
        return userID;
    }

}
