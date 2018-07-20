package Weapons.Melee;

import Weapons.Weapon;

abstract class Melee implements Weapon {
    abstract int getDurability();
    abstract int swingSpeed();
}
