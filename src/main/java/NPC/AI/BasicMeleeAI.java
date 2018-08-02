package NPC.AI;

import Rendering.ObjectRenderer;
import Utility.GameObject;
import Utility.HeldItem;
import Utility.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class BasicMeleeAI extends BaseAI{

    private Sprite target;
    private Sprite self;

    public BasicMeleeAI(Sprite target) {
        this.target = target;
    }

    public void nextMove() {
        try {
            //Determine if target is to the left or right of self
            boolean jump = false;
            if (self.getxPos() < target.getxPos()) { //Self is to the left of Target.
                for (GameObject i : ObjectRenderer.getImages()) {
                    if (!(i instanceof Sprite)) {
                        if (self.isTouchingWest(i)) {
                            jump = true;
                        }
                    }
                }

                if (jump) {
                    onJumpMovement();
                    onRightMovement();
                } else {
                    onRightMovement();
                }
            } else if (self.getxPos() > (target.getxPos() + target.getxBound())) { //Self is to the right of Target.
                for (GameObject i : ObjectRenderer.getImages()) {
                    if (!(i instanceof Sprite)) {
                        if (self.isTouchingEast(i)) {
                            jump = true;
                        }
                    }
                }

                if (jump) {
                    onJumpMovement();
                    onLeftMovement();
                } else {
                    onLeftMovement();
                }
            }
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onLeftMovement() {
        try {
            boolean move = true;
            for (GameObject i : ObjectRenderer.getImages()) {
                if (i != self && !(i instanceof HeldItem)) {
                    if (self.isTouchingEast(i) && !self.isTouchingNorth(i)) {
                        move = false;
                    }
                }
            }
            if (move) {
                self.moveSpritePosBy(-0.01,0.0);
            }
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onRightMovement() {
        try {
            boolean move = true;
            for (GameObject i : ObjectRenderer.getImages()) {
                if (i != self && !(i instanceof HeldItem)) {
                    if (self.isTouchingWest(i) && !self.isTouchingNorth(i)) {
                        move = false;
                    }
                }
            }
            if (move) {
                self.moveSpritePosBy(0.01,0.0);
            }
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onJumpMovement() {
        try {
            self.moveSpritePosBy(0.0,0.05);
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onDownMovement() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onPrimaryAttack() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onSecondaryAttack() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onAttacked() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void onHealthGain() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the " + this.getClass().getName() + "!");
        }
    }

    public void supplySprite(Sprite in) {
        self = in;
    }
}
