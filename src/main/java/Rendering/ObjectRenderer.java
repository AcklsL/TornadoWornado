package Rendering;

import Maps.ObjectMap;
import UI.Healthbar;
import UI.Overlay;
import Utility.GLInstruct;

import javax.imageio.ImageIO;
import javax.media.opengl.GLAutoDrawable;
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
    private static ArrayList<Overlay> overlays;
    private static ArrayList<Projectile> projectiles;
    private static ArrayList<GameObject> toPurge;

    private GLInstruct instruct;

    public ObjectRenderer() {
        images = new ArrayList<GameObject>();
        sprites = new ArrayList<Sprite>();
        heldItems = new ArrayList<HeldItem>();
        healthbars = new ArrayList<Healthbar>();
        overlays = new ArrayList<Overlay>();
        projectiles = new ArrayList<Projectile>();
        toPurge = new ArrayList<GameObject>();
    }

    public static void addOverlay(Overlay in) {
        overlays.add(in);
    }

    public static void addObjectToScreen(GameObject image) {
        images.add(image);
        if (image instanceof Sprite) {
            sprites.add((Sprite) image);
            images.add(((Sprite) image).getHealthbar());
            healthbars.add(((Sprite) image).getHealthbar());
        }
        if (image instanceof Projectile) {
            projectiles.add((Projectile) image);
        }
        if (image instanceof HeldItem) {
            heldItems.add((HeldItem) image);
        }
    }

    public static void addMapToScreen(ObjectMap in) {
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

    public static void purge(GameObject in) {
        toPurge.add(in);
    }

    public static void filter() {
        for (GameObject i : toPurge) {
            images.remove(i);
            if (i instanceof Sprite) {
                sprites.remove(i);
            } else if (i instanceof HeldItem) {
                heldItems.remove(i);
            } else if (i instanceof Projectile) {
                projectiles.remove(i);
            }
        }
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

    public static ArrayList<Projectile> getProjectiles() {return projectiles;}

    /**
     * Run only AFTER adding all images req. to screen.
     */
    public void createInstruct() {
        if (!images.isEmpty() && !overlays.isEmpty()) {
            instruct = new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) {
                    for (GameObject i : images) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                    for (Overlay i : overlays) {
                        i.getInstruct().instruct(glAutoDrawable);
                    }
                }
            };
        }
    }
}
