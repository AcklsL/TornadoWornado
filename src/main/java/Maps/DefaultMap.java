package Maps;

import Rendering.FileHolder;
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

        WestBorder = new GameObject("West Wall - Default Map", false, false, -2.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(), false, WestBorder, glAutoDrawable);
            }
        });

        EastBorder = new GameObject("East Wall - Default Map", false,false, 5.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false,EastBorder,glAutoDrawable);
            }
        });

        NorthBorder = new GameObject("North Wall - Default Map", false,false, -2.0, 0.8, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false,NorthBorder,glAutoDrawable);
            }
        });

        SouthBorder = new GameObject("South Wall - Default Map", false,false, -2.0, -1.0, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false,SouthBorder,glAutoDrawable);
            }
        });

        PlatformA = new GameObject("Platform A - Default Map", false,false, 0.2, -0.6, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false, PlatformA, glAutoDrawable);
            }
        });

        PlatformB = new GameObject("Platform B - Default Map", false,false, 0.7, -0.4, 0.4, 0.3, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false,PlatformB,glAutoDrawable);
            }
        });

        PlatformC = new GameObject("Platform C - Default Map", false,false, -0.2, -0.8, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(FileHolder.getBon(),false,PlatformC,glAutoDrawable);
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
