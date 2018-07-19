package Model;

import View.Player;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Map {
    private String name;
    private int width;
    private int height;

    private Tile[][] tileMap;


    public Map(String name) {
        this.name = name;
        loadMap();
        assignNeighbours();
    }

    public void spawnEntity(Player owner, int xPos, int yPos, String entityName) {
        //TODO get unit from a map, based off name input
        tileMap[xPos][yPos].setEntity(
                new Unit(owner, entityName, 100, 10, 1)
        );
    }

    private void loadMap() {
        File mapFile = new File("./Resources/Maps/" + name + ".map");
        int yIndex = 0;
        try {
            Scanner sc = new Scanner(mapFile);
            width = sc.nextInt();
            height = sc.nextInt();
            tileMap = new Tile[width][height];
            sc.nextLine();

            // Rest of the file is map tiles
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner lineScanner = new Scanner(line);
                int xIndex = 0;
                while(lineScanner.hasNext()) {
                    String tile = lineScanner.next();
                    String tileImage = "Image path not assigned";
                    switch(tile){
                        case "=" :
                            tileImage = "DeepWater(88x44).png";
                            break;
                        case "-" :
                            tileImage = "ShallowWater(86x43).png";
                            break;
                        case "s" :
                            tileImage = "Sand(86x43).png";
                            break;
                        case "g" :
                            tileImage = "Grass(86x43).png";
                            break;
                        case "m" :
                            tileImage = "Dirt(86x43).png";
                            tileMap[xIndex][yIndex] = new Tile(tileImage, "Mountain(86x60).png");
                            xIndex++;
                            continue;
                        case "v" :
                            xIndex++;
                            continue;
                    }
                    tileMap[xIndex][yIndex] = new Tile(tileImage, null);
                    xIndex++;
                }
                yIndex++;
                System.out.println(line);
            }

            sc.close();
        } catch(IOException e) {
            e.printStackTrace();
            DefaultGroovyMethods.println(this, "Failed to load map /Resources/Maps/" + name + ".map");
        }
        System.out.println(name + " loaded successfully!\n");
    }

    private void assignNeighbours() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Tile tile = tileMap[x][y];
                if(tile == null) {continue;}

                if(y > 1 && tileMap[x][y] != null) {tile.neighbours.add(tileMap[x][y-2]);} //Top
                if(y < height-2  && tileMap[x][y+2] != null) {tile.neighbours.add(tileMap[x][y+2]);} //Bottom
                if(y % 2 == 0){ //Always even 'y' values, starting from y = 0
                    if(x > 0 && y > 0 && tileMap[x-1][y-1] != null){tile.neighbours.add(tileMap[x-1][y-1]);} //Top left
                    if(y > 0 && tileMap[x][y-1] != null){tile.neighbours.add(tileMap[x][y-1]);} //Top right
                    if(y < width-1 && tileMap[x][y+1] != null){tile.neighbours.add(tileMap[x][y+1]);} //Bottom right
                    if(x > 0 && y < height-1 && tileMap[x-1][y+1] != null){tile.neighbours.add(tileMap[x-1][y+1]);} //Bottom left
                }
                else{ //Always odd 'y' values, starting from y = 1
                    if(tileMap[x][y-1] != null){tile.neighbours.add(tileMap[x][y-1]);} //Top left
                    if(x < width-1 && tileMap[x+1][y-1] != null){tile.neighbours.add(tileMap[x+1][y-1]);} //Top right
                    if(x < width-1 && y < height-2 && tileMap[x+1][y+1] != null){tile.neighbours.add(tileMap[x+1][y+1]);} //Bottom right
                    if(y < height-1 && tileMap[x][y+1] != null){tile.neighbours.add(tileMap[x][y+1]);} //Bottom left
                }
            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
