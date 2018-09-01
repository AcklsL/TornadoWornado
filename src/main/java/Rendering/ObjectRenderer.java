package Rendering;

import Maps.Layer;
import Maps.ObjectMap;
import UI.Healthbar;
import Utility.GLInstruct;
import Utility.GameObject;
import Utility.HeldItem;
import Utility.Sprite;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectRenderer {

    /**
     * Image ordering:
     * 0 - Character. Always
     * 1 - Map
     */
    private static ArrayList<GameObject> images;
    private static ArrayList<Sprite> sprites;
    private static ArrayList<HeldItem> heldItems;
    private static ArrayList<Healthbar> healthbars;

    private GLInstruct instruct;

    public ObjectRenderer() {
        images = new ArrayList<GameObject>();
        sprites = new ArrayList<Sprite>();
        heldItems = new ArrayList<HeldItem>();
        healthbars = new ArrayList<Healthbar>();
    }

    public void addObjectToScreen(GameObject image) {
        images.add(image);
        if (image instanceof Sprite) {
            sprites.add((Sprite) image);
            images.add(((Sprite) image).getHealthbar());
            healthbars.add(((Sprite) image).getHealthbar());
        }
        if (image instanceof HeldItem) {
            heldItems.add((HeldItem) image);
        }
    }

    public void addMapToScreen(ObjectMap in) {
        for (GameObject object : in.getMap()) {
            addObjectToScreen(object);
        }
        for (Sprite sprite : in.getMapSprites()) {
            addObjectToScreen(sprite);
        }
    }

    public static BufferedImage fileToBufferedImage(File in) {
        BufferedImage BImg;
        try {
            BImg = ImageIO.read(in);
            return BImg;
        } catch (IOException e) {
            e.getCause();
        }
        return null;
    }

    public GLInstruct getInstruct() {
        return instruct;
    }

    public void setInstruct(GLInstruct instruct) {
        this.instruct = instruct;
    }

    public static ArrayList<GameObject> getImages() {
        return images;
    }

    public static ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public static ArrayList<HeldItem> getHeldItems() {
        return heldItems;
    }

    /**
     * Run only AFTER adding all images req. to screen.
     */
    public void createInstruct() {
        if (!images.isEmpty()) {
            instruct = new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) {
                    for (GameObject i : images) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                }
            };
        }
    }
}
