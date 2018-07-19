package View.Display;

import Model.Entity;
import Model.Map;
import Model.Tile;
import Utilities.Resources;
import View.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameWindow extends Pane {
    private final double x1 = (42.0 + 86.0) / 86.0;
    private final double x2 = 64.0 / 86.0; // 64,86 or 65,87 (original: 65,86)

    private Map currentMap = Resources.getMap("Island");
    private Player user;

    private double canvasWidth = (currentMap.getWidth()*Resources.TILE_WIDTH*x1) + (Resources.TILE_WIDTH/4) + 1;
    private double canvasHeight = (currentMap.getHeight()/2) * Resources.TILE_HEIGHT;
    private Canvas floor = new Canvas(canvasWidth, canvasHeight);
    private Canvas entities = new Canvas(canvasWidth, canvasHeight);

    int count = 0;
    public GameWindow(Player user) {
        this.user = user;
        setTilePositions();
        getChildren().add(floor);
        getChildren().add(entities);

        currentMap.spawnEntity(user, 2, 18, "Sonic(30x43).png");

        AnimationTimer timer = new AnimationTimer() {
            private long last = 0;
            @Override
            public void handle(long now) {
                if(now - last >= 16666667) { //60 fps
//                    currentMap.spawnEntity(user, (int)(Math.random()*14), (int)(Math.random()*59), "Sonic(30x43).png");
                    redraw();
                    last = now;
                }
            }
        };
        timer.start();
    }

    private void redraw() {
        //Draw a black background to the panel (scales with map size)
        GraphicsContext gc = floor.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                currentMap.getWidth() * Resources.TILE_WIDTH * 1.75,
                currentMap.getHeight() * Resources.TILE_HEIGHT);
        //Draw map
        Tile[][] tiles = currentMap.getTileMap();
        for(int x = 0; x < currentMap.getWidth(); x++) {
            for(int y = 0; y < currentMap.getHeight(); y++) {
                drawFloor(tiles[x][y]);
                drawEntity(tiles[x][y]);
            }
        }
        if(user.getSelected() != null) {
            //TODO draw selection path
        }
    }

    private void setTilePositions() {
        Tile[][] tiles = currentMap.getTileMap();
        int xCount = 0;
        for(double x = 0; x < currentMap.getWidth()*x1; x+=x1) {
            int yCount = 0;
            for(double y = 0; y < currentMap.getHeight()*0.5; y+=0.5) {
                if(tiles[xCount][yCount] == null) {
                    if(x == 0) { // Addition pixel is needed (cant explain, just trust me idiot)
                        x+=0.01162790697; //TODO turn into math up top, rather than hard coded calculation result
                    }
                    yCount++;
                    continue;
                } else if(y % 1 == 0) {
                    tiles[xCount][yCount].setXPosition(x*Resources.TILE_WIDTH);
                    tiles[xCount][yCount].setYPosition(y*(Resources.TILE_HEIGHT-1));
                } else {
                    tiles[xCount][yCount].setXPosition((x*Resources.TILE_WIDTH)+(Resources.TILE_WIDTH*x2));
                    tiles[xCount][yCount].setYPosition(y*(Resources.TILE_HEIGHT-1));
                }
                if(x == 0) {
                    x+=0.01162790697;
                }
                yCount++;
            }
            xCount++;
        }
    }

    private void drawFloor(Tile tile){
        GraphicsContext gc = floor.getGraphicsContext2D();
        if(tile != null) {
            gc.drawImage(tile.getFloorImage(),
                    tile.getXPosition(),
                    tile.getYPosition(),
                    Resources.TILE_WIDTH,
                    Resources.TILE_HEIGHT);
            if(tile.getObstruction() != null) {
                gc.drawImage(Resources.getImage("Tiles/Misc/"+tile.getObstruction()),
                        tile.getXPosition(),
                        tile.getYPosition()-17); //17 is default obstruction offset
            }
        }
    }

    private void drawEntity(Tile tile) {
        GraphicsContext gc = entities.getGraphicsContext2D();
        if(tile != null && tile.getEntity() != null) {
            Entity entity = tile.getEntity();
            double entityWidth = entity.getImage().getWidth();
            double entityHeight = entity.getImage().getHeight();
            double xPos = tile.getXPosition() + (Resources.TILE_WIDTH/2) - (entityWidth/2);
            double yPos = tile.getYPosition() + (Resources.TILE_HEIGHT/2) - entityHeight + 10;
            gc.drawImage(tile.getEntity().getImage(),
                    xPos,
                    yPos);
        }
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

}
