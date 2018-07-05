package Utility;

import Utility.GLInstruct;


public class GameObject {

    private String identifier;
    private double x;
    private double y;
    private double xBound;
    private double yBound;
    private GLInstruct render;
    private boolean isBackground;

    private final double boundTolerance = 0.001; // MUST BE LESS THAN 0.1! Otherwise things get janky.

    public GameObject(String identity, boolean background, double xPos, double yPos, double xBound, double yBound, GLInstruct objectRender) {
        identifier = identity;
        x = xPos;
        y = yPos;
        this.xBound = xBound;
        this.yBound = yBound;
        render = objectRender;
        isBackground = background;
    }

    public boolean isBackground() {
        return isBackground;
    }

    public void setBackground(boolean background) {
        isBackground = background;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public GLInstruct getRenderInstructions() {
        return render;
    }

    public void setRenderInstructions(GLInstruct render) {
        this.render = render;
    }

    public double getxBound() {
        return xBound;
    }

    public void setxBound(double xBound) {
        this.xBound = xBound;
    }

    public double getyBound() {
        return yBound;
    }

    public void setyBound(double yBound) {
        this.yBound = yBound;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void movePosBy(double xx, double yy) {
        x += xx;
        y += yy;
    }

    public void movePosTo(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public boolean isTouchingWest(GameObject target) {
        double half = target.x + (target.xBound/2);
        return (x + xBound >= target.x) && (y + yBound > target.y) && (target.y + target.yBound > y) && ((x + xBound) < half);
    }

    public boolean isTouchingEast(GameObject target) {
        double half = target.x + (target.xBound/2);
        return ((target.x + target.xBound) >= x) && (y + yBound > target.y) && (target.y + target.yBound > y) && (x >= half);
    }

    public boolean isTouchingSouth(GameObject target) {
        double half = target.y + (target.yBound/5);
        double jankFix = 0.03;
        return (((target.y < 0 ? target.y - yBound - jankFix : target.y + yBound + jankFix) - y) <= boundTolerance) && (x + xBound >= target.x) && (x <= target.x + target.xBound) && (y < half);
    }

    public boolean isTouchingNorth(GameObject target) {
        double half = target.y + (target.yBound/2);
        return ((Math.abs((y) - (target.y + target.yBound))) < boundTolerance) && (x + xBound >= target.x) && (x <= target.x + target.xBound) && (y >= half);
    }
}
