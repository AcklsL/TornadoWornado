package Weapons.Melee;

import Utility.GLInstruct;
import Utility.HeldItem;
import Utility.Sprite;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Iron_Shortsword extends Melee{

    private final int durability = 100;
    private final int damage = 5;
    private final int swingSpeed = 500;
    private boolean canSwing;
    private HeldItem image; //PLACEHOLDER IMAGE
    private final String name = "Iron_Shortsword";
    private final RARITIES rarity = RARITIES.COMMON;
    private GLInstruct pre_equip;

    public Iron_Shortsword(Sprite holder) {
        pre_equip = holder.getRenderInstructions();
        image = new HeldItem("Iron_Shortsword", holder, 0.055,0.055, new File("/Users/560372/OneDrive/TornadoWornado/src/main/java/Assets/nou.png"));
        holder.setItem(this);
        canSwing = true;
    }

    public void unequip() {
        image.getHolder().setRenderInstructions(pre_equip);
        //image = null;
    }

    public boolean canSwing() {
        return canSwing;
    }

    public void setCanSwing(boolean in) {
        canSwing = in;
    }

    public int getSwingSpeed() {
        return swingSpeed;
    }

    public HeldItem getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Sprite getHolder() {
        return image.getHolder();
    }

    public RARITIES getRarity() {
        return rarity;
    }

    int getDurability() {
        return durability;
    }

    int swingSpeed() {
        return swingSpeed;
    }

    public void onLeftClick() {
        if (canSwing) {
            image.swing(this);
            canSwing = false;
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    canSwing = true;
                }
            },swingSpeed);
        }
    }

    public void onRightClick() {

    }

    public void onMiddleClick() {

    }

    public int getDamage() {
        return damage;
    }

    public HeldItem getHeldItem() {
        return image;
    }
}
