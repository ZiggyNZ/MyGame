package Model;

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
        //TODO declare neighbours
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
        System.out.println("Loaded successfully");
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
