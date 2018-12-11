package Abilities;

import Utility.GLInstruct;
import Utility.Instruct;

import java.io.File;

public abstract class AbilityBase {
    public static String strPthToAbilityImages() {
        return "./src/main/java/Assets/AbilityImages/";
    }
    public abstract File getImage();
    public abstract String getName();
    public abstract double getManaCost();
    public abstract double getDamage();
    public abstract Instruct getEffectInstructions();
    public abstract GLInstruct getRenderInstructions();
    public abstract void cooldownUpdate();
}
