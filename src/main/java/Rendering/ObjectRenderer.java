package Rendering;

import Abilities.AbilityBase;
import Abilities.DefaultTestE;
import Abilities.DefaultTestQ;
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
    //private static ArrayList<GameObject> images = new ArrayList<GameObject>();  DEPRECIATED
    private static ArrayList<Tile> tiles = new ArrayList<Tile>();
    private static ArrayList<GameObject> onHold = new ArrayList<GameObject>(); //All items that are not going to be rendered.
    private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private static ArrayList<HeldItem> heldItems = new ArrayList<HeldItem>();
    private static ArrayList<Healthbar> healthbars = new ArrayList<Healthbar>();
    private static ArrayList<Overlay> overlays = new ArrayList<Overlay>();
    private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private static ArrayList<GameObject> toPurge = new ArrayList<GameObject>();
    private static AbilityBase[] abilities = {new DefaultTestQ(), new DefaultTestE()};

    private static GLInstruct instruct;

    public static void addOverlay(Overlay in) {
        overlays.add(in);
    }

    public static void addObjectToScreen(GameObject image) {
        if (image instanceof Sprite) {
            sprites.add((Sprite) image);
            healthbars.add(((Sprite) image).getHealthbar());
        } else if (image instanceof Projectile) {
            projectiles.add((Projectile) image);
        } else if (image instanceof HeldItem) {
            heldItems.add((HeldItem) image);
        } else if (image instanceof Tile) {
            tiles.add((Tile) image);
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

    public static ArrayList<GameObject> getOnHold() {
        return onHold;
    }

    public static void setAbilityQ(AbilityBase i) {
        abilities[0] = i;
    }

    public static void setAbilityE(AbilityBase i) {
        abilities[1] = i;
    }

    public static void show(GameObject i) {
        addObjectToScreen(i);
        onHold.remove(i);
    }

    public static void hide(GameObject i) {
        onHold.add(i);
        if (i instanceof Sprite) {
            sprites.remove(i);
            healthbars.remove(((Sprite) i).getHealthbar());
        } else if (i instanceof Projectile) {
            projectiles.remove(i);
        } else if (i instanceof HeldItem) {
            heldItems.remove(i);
        } else if (i instanceof Tile) {
            tiles.remove(i);
        }
    }

    public static AbilityBase getAbilityQ() {
        return abilities[0];
    }

    public static AbilityBase getAbilityE() {
        return abilities[1];
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
            if (i instanceof Sprite) {
                sprites.remove(i);
            } else if (i instanceof HeldItem) {
                heldItems.remove(i);
            } else if (i instanceof Projectile) {
                projectiles.remove(i);
            } else if (i instanceof Tile) {
                if (tiles.contains(i)) tiles.remove(i);
            }
        }
    }

    public static GLInstruct getInstruct() {
        return instruct;
    }

    public static void setInstruct(GLInstruct instructa) {
        instruct = instructa;
    }

    public static ArrayList<Tile> getTiles() {
        return tiles;
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
    public static void createInstruct() {
        if (!tiles.isEmpty() && !overlays.isEmpty() && !sprites.isEmpty()) {
            instruct = new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) {
                    for (GameObject i : tiles) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                    for (GameObject i : sprites) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                    for (GameObject i : projectiles) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                    for (GameObject i : healthbars) {
                        i.getRenderInstructions().instruct(glAutoDrawable);
                    }
                    for (Overlay b : overlays) {
                        b.getInstruct().instruct(glAutoDrawable);
                    }
                    for (int i = 0; i < abilities.length; i++) {
                        abilities[i].getRenderInstructions().instruct(glAutoDrawable);
                    }
                }
            };
        }
    }
}
