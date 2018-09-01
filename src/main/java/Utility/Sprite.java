package Utility;

import NPC.AI.BaseAI;
import NPC.AI.NoAI;
import Rendering.ObjectRenderer;
import UI.Healthbar;
import Weapons.Weapon;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sprite extends GameObject {

    private File charSprite;
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private onHit onHit;
    private BaseAI AI;

    private Weapon hasItem;

    private int health;
    private int maxHealth;
    private Healthbar healthbar;

    public Sprite(String identity,int health, final double x, final double y, final double w, final double h, final File spriteImage, onHit in, BaseAI ai) {
        super(identity,false, false, x, y, w, h, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        this.health = health;
        maxHealth = health;
        onHit = in;
        hasItem = null;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(charSprite, xPos, yPos, width, height, glAutoDrawable);
            }
        });
        charSprite = spriteImage;
        AI = ai;
        healthbar = new Healthbar(this);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(Healthbar healthbar) {
        this.healthbar = healthbar;
    }

    public BaseAI getAI() {
        return AI;
    }

    public void setAI(BaseAI AI) {
        this.AI = AI;
    }

    public File getCharSprite() {
        return charSprite;
    }

    public void setCharSprite(File charSprite) {
        this.charSprite = charSprite;
    }

    public void moveSpritePosBy(double x, double y) {
        super.movePosBy(x,y);
        xPos += x;
        yPos += y;
    }

    public int getHealth() {
        return health;
    }

    public onHit getOnHit() {
        return onHit;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(int dmg) {
        this.health += dmg;
        if (health <= 0) {
            super.setBackground(true);
            super.setIgnore(true);
            super.setRenderInstructions(new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) { }
            });
            getHealthbar().clear();
            setHealthbar(new Healthbar());
            setAI(new NoAI());
        }
    }

    public void moveSpritePosTo(double x, double y) {
        super.movePosTo(x,y);
        xPos = x;
        yPos = y;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }


    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean holdingItem() {
        return hasItem != null;
    }

    public Weapon getItem() {
        return hasItem;
    }

    public void setItem(Weapon in) {
        hasItem = in;
    }
}
