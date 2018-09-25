package Rendering;

import Rendering.GameObject;
import Rendering.ObjectRenderer;
import Rendering.Sprite;
import Utility.GLInstruct;
import Utility.QuickDraw;
import Weapons.Weapon;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class HeldItem extends GameObject {

    private Sprite holder;
    private File img;
    private double w;
    private double h;

    public HeldItem(String identity, final Sprite spriteHolder, final double xBound, final double yBound, final File image){
        super(identity, false, true, spriteHolder.getxPos() + spriteHolder.getxBound(), spriteHolder.getyPos(), xBound, yBound, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        holder = spriteHolder;
        img = image;
        w = xBound;
        h = yBound;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, spriteHolder.getX() + spriteHolder.getWidth(), spriteHolder.getY(), xBound, yBound, glAutoDrawable);
            }
        });
    }

    public void swing(Weapon in) {
        for (Sprite i : ObjectRenderer.getSprites()) {
            if (i != holder && this.isTouching(i)) {
                i.changeHealth(in.getDamage());
                i.getHealthbar().setPercent(i.getHealth() / i.getMaxHealth());
                i.getOnHit().onHitAction(in);
            }
        }
    }

    public Sprite getHolder() {
        return holder;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
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
}
