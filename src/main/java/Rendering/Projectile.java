package Rendering;

import Abilities.AbilityBase;
import KeyBinds.MouseEvents;
import UI.Overlay;
import Utility.GLInstruct;
import Utility.QuickDraw;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class Projectile extends GameObject{

    private double xVelocity;
    private double yVelocity;
    private static double x;
    private static double y;
    private int damage;
    private Sprite ignoreSprite;

    public Projectile(String identity, double xVelocityIn, double yVelocityIn, int dmg, double xi, double yi, final double w, final double h, final File image) {
        super(identity, false, false, xi, yi, w, h, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }});
        xVelocity = xVelocityIn;
        yVelocity = yVelocityIn;
        damage = dmg;
        this.x = xi;
        this.y = yi;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, Projectile.super.getX(), Projectile.super.getY(), w, h, glAutoDrawable);
            }
        });
    }

    public Projectile(String identity, double xVelocityIn, double yVelocityIn, int dmg, double xi, double yi, final File image) {
        super(identity, false, false, xi, yi, 0.125, 0.125, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        xVelocity = xVelocityIn;
        yVelocity = yVelocityIn;
        damage = dmg;
        this.x = xi;
        this.y = yi;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, Projectile.super.getX(), Projectile.super.getY(), 0.0625, 0.0625, glAutoDrawable);
            }
        });
    }

    public Projectile(String identity, double xVelocityIn, double yVelocityIn, int dmg, double xi, double yi, final double w, final double h, final File image, Sprite in) {
        super(identity, false, false, xi, yi, w, h, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }});
        xVelocity = xVelocityIn;
        yVelocity = yVelocityIn;
        damage = dmg;
        this.x = xi;
        this.y = yi;
        ignoreSprite = in;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, Projectile.super.getX(), Projectile.super.getY(), w, h, glAutoDrawable);
            }
        });
    }

    public Projectile(String identity, double xVelocityIn, double yVelocityIn, int dmg, double xi, double yi, final File image, Sprite in) {
        super(identity, false, false, xi, yi, 0.125, 0.125, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        xVelocity = xVelocityIn;
        yVelocity = yVelocityIn;
        damage = dmg;
        this.x = xi;
        this.y = yi;
        ignoreSprite = in;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, Projectile.super.getX(), Projectile.super.getY(), 0.0625, 0.0625, glAutoDrawable);
            }
        });
    }

    public void move() {
        super.movePosBy(xVelocity,yVelocity);
        for (GameObject i : ObjectRenderer.getImages()) {
            if (!(i instanceof Projectile) && i != ignoreSprite && (!super.onScreen() || super.isTouching(i))) {
                if (i instanceof Sprite) {
                    Sprite is = (Sprite) i;
                    is.changeHealth(damage);
                    is.getHealthbar().setPercent(is.getHealth() / is.getMaxHealth());
                }
                super.setIgnore(true);
                ObjectRenderer.purge(this);
                break;
            }
        }
    }

    private static final double flightSpeed = 10.0;
    public static Projectile genProjectile(String identity, File image) {
        return new Projectile(identity, -Overlay.cnvrtCanvasINTtoDBL(MouseEvents.getMouseX()) / flightSpeed,
                Overlay.cnvrtCanvasINTtoDBL(MouseEvents.getMouseY()) / flightSpeed, -1, 0.0,0.0, image);
    }

    public static Projectile genProjectile(String identity, File image, Sprite ignore) {
        double mouseX = Overlay.cnvrtCanvasINTtoDBL(MouseEvents.getMouseX());
        double mouseY = Overlay.cnvrtCanvasINTtoDBL(MouseEvents.getMouseY());
        double xVelocity = ignore.getX() > 0 ? (mouseX > 0 ? (mouseX - ignore.getX()) : -(Math.abs(mouseX) - ignore.getX())) : (mouseX > 0 ? Math.abs(ignore.getX()) - mouseX : Math.abs(ignore.getX()) + Math.abs(mouseX));
        double yVelocity = ignore.getY() > 0 ? (mouseY > 0 ? mouseY - ignore.getY() : -(Math.abs(mouseY) - ignore.getY())) : (mouseY > 0 ? Math.abs(ignore.getY()) + mouseY : Math.abs(ignore.getY()) - Math.abs(mouseY));
        xVelocity /= flightSpeed;
        yVelocity /= flightSpeed;
        return new Projectile(identity, xVelocity, yVelocity, -1, ignore.getX(),ignore.getY() + (ignore.getyBound()/2), image, ignore);
    }
}
