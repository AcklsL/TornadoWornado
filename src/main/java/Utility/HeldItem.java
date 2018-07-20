package Utility;

import Rendering.ObjectRenderer;
import Weapons.Weapon;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class HeldItem extends GameObject {

    Sprite holder;

    public HeldItem(String identity, final Sprite spriteHolder, final double xBound, final double yBound, final File image){
        super(identity, false, spriteHolder.getxPos() + spriteHolder.getxBound(), spriteHolder.getyPos(), xBound, yBound, new GLInstruct() {
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
        System.out.println("Swing!");
        for (Sprite i : ObjectRenderer.getSprites()) {
            if (i != holder && isTouching(i)) {
                i.changeHealth(in.getDamage());
                i.getOnHit().onHitAction(in);
            }
        }
    }

    public boolean isTouching(GameObject in) {
        if (this.isTouchingEast(in) || this.isTouchingNorth(in) || this.isTouchingSouth(in) || this.isTouchingWest(in)) {
            return true;
        } else {
            return false;
        }
    }

    public Sprite getHolder() {
        return holder;
    }
}
