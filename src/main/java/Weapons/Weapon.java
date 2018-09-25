package Weapons;

import Rendering.HeldItem;
import Rendering.Sprite;

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
