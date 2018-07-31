package Utilities

import Model.Map
import javafx.scene.image.Image

class Resources {
    public static final int TILE_WIDTH = 86
    public static final int TILE_HEIGHT = 43
    private static java.util.Map<String, Map> maps = new HashMap<>()
    private static java.util.Map<String, Image> images = new HashMap<>()

    static void initialiseMaps(){ //TODO
        maps.put("Sandbox", new Map("Sandbox"))
        maps.put("Island", new Map("Island"))
        maps.put("Homeworld", new Map("Homeworld"))
    }

    static Map getMap(String mapName){
        return maps.get(mapName)
    }

    static Image getImage(String imageName){
        if(images.containsKey(imageName)){return images.get(imageName)}
        Image newImage = null
        try{
            newImage = new Image(new FileInputStream("./resources/"+imageName))
        }catch(Exception e){e.printStackTrace();}
        images.put(imageName, newImage)
        return newImage;
    }
}
