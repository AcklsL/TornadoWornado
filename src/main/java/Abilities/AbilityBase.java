package Abilities;

import Utility.GLInstruct;

import java.io.File;

public abstract class AbilityBase {
    public static String strPthToAbilityImages() {
        return "./src/main/java/Assets/AbilityImages/";
    }
    abstract File getImage();
    abstract String getName();
    abstract double getManaCost();
    abstract double getDamage();
    abstract GLInstruct getEffectInstructions();
}
