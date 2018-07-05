package Maps;

import Utility.GameObject;

import java.util.ArrayList;

public class Layer {

    private String layerName;
    private ArrayList<GameObject> gameObjects;

    public Layer(String name) {
        layerName = name;
        gameObjects = new ArrayList<GameObject>();
    }

    public Layer(String name, ArrayList<GameObject> in) {
        layerName = name;
        gameObjects = in;
    }

    public String getLayerName() {
        return layerName;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addToGameObjects(GameObject in) {
        gameObjects.add(in);
    }
}
