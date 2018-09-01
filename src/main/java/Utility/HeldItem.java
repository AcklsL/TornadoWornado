package Utility;

import Rendering.ObjectRenderer;
import Weapons.Weapon;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class HeldItem extends GameObject {

    Sprite holder;

    public HeldItem(String identity, final Sprite spriteHolder, final double xBound, final double yBound, final File image){
        super(identity, false, true, spriteHolder.getxPos() + spriteHolder.getxBound(), spriteHolder.getyPos(), xBound, yBound, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        holder = spriteHolder;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, spriteHolder.getX() + spriteHolder.getWidth(), spriteHolder.getY(), xBound, yBound, glAutoDrawable);
            }
        });
    }

    public void swing(Weapon in) {
        for (Sprite i : ObjectRenderer.getSprites()) {
            if (i != holder && this.isTouching(holder,i)) {
                i.changeHealth(in.getDamage());
                i.getHealthbar().setPercent((double) i.getHealth() / (double) i.getMaxHealth());
                i.getOnHit().onHitAction(in);
            }
        }
    }

    public Sprite getHolder() {
        return holder;
    }
}
