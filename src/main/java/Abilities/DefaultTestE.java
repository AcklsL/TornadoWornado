package Abilities;

import Rendering.ObjectRenderer;
import Rendering.Sprite;
import Utility.GLInstruct;
import Utility.Instruct;
import Utility.QuickDraw;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class DefaultTestE extends AbilityBase{

    private static String name = "Default Test Ability E";
    private static File image = new File(AbilityBase.strPthToAbilityImages() + "ETestAbility.png");
    private static double manaCost = 5.0;
    private static double damage = 10.0;
    private static GLInstruct imageInstructions;
    private static Instruct effectInstructions = new Instruct() {
        public void instruct() {
            for (Sprite i : ObjectRenderer.getSprites()) {
                if (i.getIdentifier().equalsIgnoreCase("Character")) {
                    i.changeHealth(50);
                    break;
                }
            }
        }
    };

    public DefaultTestE() {
        imageInstructions = new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(image, false, 0.25, -1.0, 0.25, 0.2, glAutoDrawable);

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

    }
}
