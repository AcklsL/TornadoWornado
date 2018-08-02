package NPC.AI;

import Utility.Sprite;

public abstract class BaseAI {
    public abstract void nextMove();
    public abstract void onLeftMovement();
    public abstract void onRightMovement();
    public abstract void onJumpMovement();
    public abstract void onDownMovement();
    public abstract void onPrimaryAttack();
    public abstract void onSecondaryAttack();
    public abstract void onAttacked();
    public abstract void onHealthGain();
    public abstract void supplySprite(Sprite in);
}
