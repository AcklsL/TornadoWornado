package Abilities;

import Rendering.ObjectRenderer;
import Rendering.Projectile;
import Rendering.Sprite;
import Utility.GLInstruct;
import Utility.Instruct;
import Utility.QuickDraw;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class DefaultTestQ extends AbilityBase{


    private static String name = "Default Test Ability Q";
    private static File image = new File(AbilityBase.strPthToAbilityImages() + "QTestAbility.jpg");
    private static double manaCost = 25.0;
    private static double damage = 10.0;
    private static int cooldownTime = 100;
    private static int currCooldownTime = 0;
    private static GLInstruct imageInstructions;
    private static boolean onCooldown = false;
    private static Instruct effectInstructions = new Instruct() {
        public void instruct() {
            Sprite ignore = null;
            if (currCooldownTime <= 0) {
                onCooldown = false;
            }
            if (!onCooldown) {
                onCooldown = true;
                currCooldownTime = cooldownTime;
                for (Sprite i : ObjectRenderer.getSprites()) {
                    if (i.getIdentifier().equalsIgnoreCase("Character")) {
                        ignore = i;
                        break;
                    }
                }
                if (ignore.getMana() - manaCost >= 0) {
                    double x = 0;
                    double y = 0.1;
                    for (int i = 0; i < 4; i++) {
                        x += 0.025;
                        y -= 0.025;
                        ObjectRenderer.addObjectToScreen(Projectile.genProjectile(this.getClass().getName() + " - Projectile", x, y, ignore, image));
                    }
                    for (int i = 0; i < 4; i++) {
                        x -= 0.025;
                        y -= 0.025;
                        ObjectRenderer.addObjectToScreen(Projectile.genProjectile(this.getClass().getName() + " - Projectile", x, y, ignore, image));
                    }
                    for (int i = 0; i < 4; i++) {
                        x -= 0.025;
                        y += 0.025;
                        ObjectRenderer.addObjectToScreen(Projectile.genProjectile(this.getClass().getName() + " - Projectile", x, y, ignore, image));
                    }
                    for (int i = 0; i < 4; i++) {
                        x += 0.025;
                        y += 0.025;
                        ObjectRenderer.addObjectToScreen(Projectile.genProjectile(this.getClass().getName() + " - Projectile", x, y, ignore, image));
                    }
                    ignore.changeMana(-manaCost);
                }
            }
        }
    };

    public DefaultTestQ() {
        imageInstructions = new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, -0.5, -1.0, 0.25, 0.2, glAutoDrawable);
                if (onCooldown) {
                    QuickDraw.drawCooldown(currCooldownTime, cooldownTime, -0.5, -1.0, 0.2, glAutoDrawable);
                }
            }
        };
    }

    public File getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getManaCost() {
        return manaCost;
    }

    public double getDamage() {
        return damage;
    }

    public Instruct getEffectInstructions() {
        return effectInstructions;
    }

    public GLInstruct getRenderInstructions() {
        return imageInstructions;
    }

    public void cooldownUpdate() {
        currCooldownTime -= currCooldownTime > 0 ? 1 : 0;
    }
}
