package View.Display;

import Model.Map;
import Model.Tile;
import Utilities.Resources;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class GameWindow extends Pane {

    private Canvas floor = new Canvas(1400, 700);
    private Canvas entities = new Canvas(1400, 700);
    private Map currentMap = Resources.getMap("Island");

    public GameWindow() {
        redrawFloor();
        redrawEntities();
        getChildren().add(floor);
        getChildren().add(entities);
    }

    private void redrawFloor() {
        GraphicsContext gc = floor.getGraphicsContext2D();
        //gc.drawImage(Resources.getImage("Tiles/Floors/Grass(86x43).png"), 0, 0, 1400, 700);
        Tile[][] tiles = currentMap.getTileMap();
        int xCount = 0;
        for(double x = 0; x < currentMap.getWidth()*1.48837209302; x+=1.48837209302) {
            int yCount = 0;
            for(double y = 0; y < currentMap.getHeight()*0.5; y+=0.5) {
                if(tiles[xCount][yCount] == null) {
                    if(x == 0) {
                        x+=0.01162790697;
                    }
                    yCount++;
                    continue;
                } else if(y % 1 == 0) {
                    gc.drawImage(tiles[xCount][yCount].getFloorImage(),
                            (int)(x*Resources.TILE_WIDTH),
                            (int)(y*(Resources.TILE_HEIGHT-1)));
                } else {
                    gc.drawImage(tiles[xCount][yCount].getFloorImage(),
                            (int)(x*Resources.TILE_WIDTH)+(int)(Resources.TILE_WIDTH*0.75518604651),
                            (int)(y*(Resources.TILE_HEIGHT-1)));
                }
                if(x == 0) {
                    x+=0.01162790697;
                }
                yCount++;
            }
            xCount++;
        }

    }

    private void redrawEntities() {
        GraphicsContext gc = entities.getGraphicsContext2D();
        gc.drawImage(Resources.getImage("Tiles/Units/Sonic(30x43).png"), 400, 200);
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

}
