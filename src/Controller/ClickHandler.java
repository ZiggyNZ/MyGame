package Controller;

import Model.Map;
import Model.Tile;
import Model.Unit;
import Utilities.Resources;
import View.Player;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class ClickHandler implements EventHandler<MouseEvent>{
    private final Player user;
    private Polygon[][] polyFrame;
    private Map map = Resources.getMap("Island");//TODO on map change; change poly frame

    public ClickHandler(Player user){ //TODO consider storing the tilemap, rather than the map object itself
        this.user = user;
        initPolyFrame();
    }//TODO a map where you input location and it returns poly frame index

    private void initPolyFrame() {
        Tile[][] tiles = map.getTileMap();
        polyFrame = new Polygon[map.getWidth()][map.getHeight()];

        for(int x = 0; x < map.getWidth(); x++) {
            for(int y = 0; y < map.getHeight(); y++) {
                Tile tile = tiles[x][y];
                polyFrame[x][y] = createPoly(tile.getXPosition(), tile.getYPosition());
            }
        }
    }

    /**
     * Create a polygon (clockwise starting from the left most point)
     *
     * @return Polygon
     */
    private Polygon createPoly(double xPos, double yPos) {
        double[] points = new double[] {
                xPos,       21 + yPos,
                21 + xPos,  yPos,
                64 + xPos,  yPos,
                85 + xPos,  21 + yPos,
                64 + xPos,  42 + yPos,
                21 + xPos,  42 + yPos
        };//When width = 86 and height = 43
        return new Polygon(points);
    }

    @Override
    public void handle(MouseEvent event) {
        for(int x = 0; x < map.getWidth(); x++) {
            for(int y = 0; y < map.getHeight(); y++) {
                if(polyFrame[x][y].contains(event.getX(), event.getY())){
                    if(event.getButton() == MouseButton.PRIMARY) {
                        leftClick(x, y);
                    }
                    else if(event.getButton() == MouseButton.SECONDARY) {
                        rightClick(x, y);
                    }
                    return;
                }
            }
        }
        //TODO consider using shift or ctrl clicks for something
        //TODO add click and drag event handler
    }

    private void leftClick(int xIndex, int yIndex) {
        Tile selected = map.getTileMap()[xIndex][yIndex];
        if(user.getSelected() == selected) {
            user.setSelected(null);
        } else {
            user.setSelected(selected);
        }
    }

    private void rightClick(int xIndex, int yIndex) {
        Tile root = user.getSelected();
        Tile goal = map.getTileMap()[xIndex][yIndex];

        if(root != null && user.owns(root.getEntity()) && goal.traversable()) {
            if(root.getEntity() instanceof Unit) {

            }
        }
    }
}
