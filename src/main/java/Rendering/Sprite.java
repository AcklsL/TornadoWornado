package Rendering;

import NPC.AI.BaseAI;
import NPC.AI.NoAI;
import Rendering.GameObject;
import Rendering.ObjectRenderer;
import UI.Healthbar;
import Utility.GLInstruct;
import Utility.QuickDraw;
import Utility.onHit;
import Weapons.Weapon;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class Sprite extends GameObject {

    private File charSprite;
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private Utility.onHit onHit;
    private BaseAI AI;
    private boolean flip;

    private double mana;
    private final double maxMana = 100.0;

    private Weapon hasItem;

    private double health;
    private double maxHealth;
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
        flip = false;
        mana = 100;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(charSprite,flip, xPos, yPos, width, height, glAutoDrawable);
            }
        });
        charSprite = spriteImage;
        AI = ai;
        if (!identity.equalsIgnoreCase("Character")) {
            healthbar = new Healthbar(this);
        } else {
            healthbar = new Healthbar();
        }
    }

    public boolean changeMana(double in) {
        if (mana <= 0) {
            if (in < 0) {
                return false;
            } else {
                if (in > 0) {
                    if (mana < maxMana) {
                        mana += in;
                    } else {
                        return false;
                    }
                } else {
                    if (mana >= 0) {
                        mana += in;
                    }
                }
                return true;
            }
        } else {
            if (in > 0) {
                if (mana < maxMana) {
                    mana += in;
                } else {
                    return false;
                }
            } else {
                if (mana >= 0) {
                    mana += in;
                }
            }
            return true;
        }
    }

    public double getMana() {
        return mana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void flip(boolean in) {
        flip = in;
        if (flip) {
            hasItem.getHeldItem().setRenderInstructions(new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) {
                    QuickDraw.quickTexture(hasItem.getHeldItem().getImg(), flip, xPos - hasItem.getHeldItem().getW(), yPos,
                            hasItem.getHeldItem().getW(), hasItem.getHeldItem().getH(), glAutoDrawable);
                }
            });
        } else {
            hasItem.getHeldItem().setRenderInstructions(new GLInstruct() {
                public void instruct(GLAutoDrawable glAutoDrawable) {
                    QuickDraw.quickTexture(hasItem.getHeldItem().getImg(), flip, xPos + width, yPos,
                            hasItem.getHeldItem().getW(), hasItem.getHeldItem().getH(), glAutoDrawable);
                }
            });
        }
    }

    public double getMaxHealth() {
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

    public double getHealth() {
        return health;
    }

    public onHit getOnHit() {
        return onHit;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(double dmg) {
        if (dmg > 0) {
            if (health < maxHealth) {
                health += dmg;
            }
        } else {
            if (health <= 0) {
                super.setBackground(true);
                super.setIgnore(true);
                super.setRenderInstructions(new GLInstruct() {
                    public void instruct(GLAutoDrawable glAutoDrawable) { }
                });
                getHealthbar().clear();
                setHealthbar(new Healthbar());
                setAI(new NoAI());
                ObjectRenderer.purge(this);
                if (hasItem != null) ObjectRenderer.purge(hasItem.getHeldItem());
            } else {
                this.health += dmg;
            }
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
