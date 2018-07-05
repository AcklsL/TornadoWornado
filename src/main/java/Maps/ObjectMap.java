package Maps;

import Utility.GameObject;
import Utility.Sprite;

import java.util.ArrayList;

public abstract class ObjectMap {
    public abstract void addToMap(GameObject toAdd);
    public abstract void addSprite(Sprite toAdd);
    public abstract ArrayList<GameObject> getMap();
    public abstract ArrayList<Sprite> getMapSprites();
    public abstract double getMoveSpeed();
    public abstract double getFallSpeed();
}
