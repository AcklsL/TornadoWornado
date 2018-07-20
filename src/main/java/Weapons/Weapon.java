package Weapons;

import Utility.HeldItem;
import Utility.Sprite;

public interface Weapon {

    public enum RARITIES{
        JUNK,COMMON,UNCOMMON,RARE,ULTRA_RARE,LEGENDARY,ETHEREAL
    }

    void onLeftClick();
    void onRightClick();
    void onMiddleClick();
    int getDamage();
    String getName();
    Sprite getHolder();
    HeldItem getHeldItem();
}
