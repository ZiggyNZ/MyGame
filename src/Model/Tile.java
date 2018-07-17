package Model;

import Utilities.Resources;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Tile implements Comparable<Tile> {

    private String obstruction; // Path to obstruction image (otherwise null)
    private Image floorImage; //TODO change obstruction to an image rather than a file path?
    private Entity entity;
    private double xPosition;
    private double yPosition;

    Tile(String floorName, String obstructed) {
        this.floorImage = Resources.getImage("Tiles/Floors/"+floorName);
        this.obstruction = obstructed;
    }

    /**
     * Returns whether this tile can be traversed by a unit.
     * <p>
     * Result is dependent on the tiles type (mountain, water, grass, etc) and any entity occupying it.
     *
     * @return A boolean; whether this tile can be traversed or not
     */
    public boolean traversable() {
        if(entity != null || obstruction != null) {
            return false;
        }
        return true;
    }

    /* *=*=*=*=*=*=*=*=*=*=*=*=*=*=* Getters and Setters *=*=*=*=*=*=*=*=*=*=*=*=*=*=* */
    public String getObstruction() {
        return obstruction;
    }

    public void setObstruction(String obstruction) {
        this.obstruction = obstruction;
    }

    public Image getFloorImage() {
        return floorImage;
    }

    public void setFloorImage(String floorName) {
        this.floorImage = Resources.getImage(floorName);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    /* *=*=*=*=*=*=*=*=*=*=*=*=*=* Search algorithm values *=*=*=*=*=*=*=*=*=*=*=*=*=* */
    public Tile parent = null;
    public double heuristic = 0;
    public double costSoFar = 0;
    public List<Tile> neighbours = new ArrayList<>();
    @Override
    public int compareTo(Tile tile){
        if(heuristic + costSoFar > tile.heuristic + tile.costSoFar){return 1;}
        else if(heuristic + costSoFar < tile.heuristic + tile.costSoFar){return -1;}
        return 0;
    }
    /* *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=* */
}
