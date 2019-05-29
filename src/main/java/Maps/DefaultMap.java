package Maps;

import Rendering.FileHolder;
import Rendering.Tile;
import Utility.GLInstruct;
import Rendering.GameObject;
import Utility.QuickDraw;
import Rendering.Sprite;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;
import java.util.ArrayList;

public class DefaultMap extends ObjectMap{

    //private Layer[] mapLayers;
    private ArrayList<GameObject> objects;
    private ArrayList<Sprite> mapSprites;

    private GameObject WestBorder;
    private GameObject SouthBorder;
    private GameObject NorthBorder;
    private GameObject EastBorder;
    private GameObject PlatformA;
    private GameObject PlatformB;
    private GameObject PlatformC;

    private final double fallSpeed = 0.01;
    private final double moveSpeed = 0.015;

    public DefaultMap() {
        objects = new ArrayList<GameObject>();
        mapSprites = new ArrayList<Sprite>();

        WestBorder = new Tile("West Wall - Default Map", -2.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(), WestBorder, glAutoDrawable);
            }
        });

        EastBorder = new Tile("East Wall - Default Map", 5.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),EastBorder,glAutoDrawable);
            }
        });

        NorthBorder = new Tile("North Wall - Default Map",true, -2.0, 0.8, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),NorthBorder,glAutoDrawable);
            }
        });

        SouthBorder = new Tile("South Wall - Default Map",true, -2.0, -1.0, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),SouthBorder,glAutoDrawable);
            }
        });

        PlatformA = new Tile("Platform A - Default Map", 0.2, -0.6, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),PlatformA, glAutoDrawable);
            }
        });

        PlatformB = new Tile("Platform B - Default Map", 0.7, -0.4, 0.4, 0.3, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),PlatformB,glAutoDrawable);
            }
        });

        PlatformC = new Tile("Platform C - Default Map", -0.2, -0.8, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),PlatformC,glAutoDrawable);
            }
        });

        objects.add(WestBorder);
        objects.add(EastBorder);
        objects.add(SouthBorder);
        objects.add(NorthBorder);
        objects.add(PlatformA);
        objects.add(PlatformB);
        objects.add(PlatformC);
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void addToMap(GameObject toAdd) {
        objects.add(toAdd);
    }

    public void addSprite(Sprite toAdd) {
        mapSprites.add(toAdd);
    }

    public ArrayList<GameObject> getMap() {
        return objects;
    }

    public ArrayList<Sprite> getMapSprites() {
        return mapSprites;
    }
}
