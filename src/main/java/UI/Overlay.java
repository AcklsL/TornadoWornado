package UI;

import KeyBinds.MouseEvents;
import Utility.GLInstruct;
import Utility.QuickDraw;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class Overlay {

    private GLInstruct instruct;
    private File image;
    private String identity;
    private double x;
    private double y;
    private double w;
    private double h;

    public Overlay(String name, File overlayImage, final double xIn, double yIn, double wIn, double hIn) {
        identity = name;
        image = overlayImage;
        x = xIn;
        y = yIn;
        w = wIn;
        h = hIn;

        instruct = new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, x, y, w, h, glAutoDrawable);
            }
        };
    }

    public void setInstruct(GLInstruct instruct) {
        this.instruct = instruct;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public GLInstruct getInstruct() {
        return instruct;
    }

    public boolean mouseOn(int xTarget, int yTarget) {
        return (MouseEvents.getMouseX() == xTarget) && (MouseEvents.getMouseY() == yTarget);
    }

    /**
     * When inputting an x value, input or output must be reversed NOT BOTH.
     * When inputting a y value, keep same.
     * @param in value to change
     * @return x or y value converted to int
     */
    public static int cnvrtCanvasDBLtoINT(double in) {
        return 1 + (((int) in) * 250);
    }

    /**
     * When inputting an x value, output must be reversed.
     * When inputting a y value, keep same.
     * @param in value to change
     * @return x or y value converted to double
     */
    public static double cnvrtCanvasINTtoDBL(int in) {
        return  (1 - (((double) in)/250.0));
    }
}
