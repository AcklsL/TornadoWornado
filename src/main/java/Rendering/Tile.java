package Rendering;

import Utility.GLInstruct;

public class Tile extends GameObject {
    private boolean persist;
    public Tile(String identity, double xPos, double yPos, double xBound, double yBound, GLInstruct objectRender) {
        super(identity, false, false, xPos, yPos, xBound, yBound, objectRender);
    }
    public Tile(String identity,boolean pers, double xPos, double yPos, double xBound, double yBound, GLInstruct objectRender) {
        super(identity, pers, false, false, xPos, yPos, xBound, yBound, objectRender);
    }
}
