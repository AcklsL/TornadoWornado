package Abilities;

import Utility.GLInstruct;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class DefaultTestQ extends AbilityBase{

    private static String name = "Default Test Ability Q";
    private static File image = new File(AbilityBase.strPthToAbilityImages() + "QTestAbility.jpg");
    private static double manaCost = 5.0;
    private static double damage = 10.0;
    private static GLInstruct effectInstructions = new GLInstruct() {
        public void instruct(GLAutoDrawable glAutoDrawable) {

        }
    };

    public DefaultTestQ() {

    }

    File getImage() {
        return image;
    }

    String getName() {
        return name;
    }

    double getManaCost() {
        return manaCost;
    }

    double getDamage() {
        return damage;
    }

    GLInstruct getEffectInstructions() {
        return null;
    }
}
