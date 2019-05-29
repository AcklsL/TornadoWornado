package NPC.AI;

import Rendering.ObjectRenderer;
import Rendering.GameObject;
import Rendering.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class BasicMeleeAI extends BaseAI{

    private Sprite target;
    private Sprite self;

    private boolean canJump = true;

    public BasicMeleeAI(Sprite target) {
        this.target = target;
    }

    public void nextMove() {
        try {
            if (!(self.getY() <= -1.0)) {
                for (GameObject i : ObjectRenderer.getTiles()) {
                    if (self.isTouchingNorth(i)) {
                        canJump = true;
                        break;
                    }
                }
            } else {
                canJump = true;
            }

            //Determine if target is to the left or right of self
            boolean jump = false;
            if (self.getxPos() < target.getxPos()) { //Self is to the left of Target.
                for (GameObject i : ObjectRenderer.getTiles()) {
                    if (self.isTouchingWest(i)) {
                        jump = true;
                    }
                }

                if (jump) {
                    onJumpMovement();
                    onRightMovement();
                } else {
                    onRightMovement();
                }
            } else if (self.getxPos() > (target.getxPos() + target.getxBound())) { //Self is to the right of Target.
                for (GameObject i : ObjectRenderer.getTiles()) {
                    if (self.isTouchingEast(i)) {
                        jump = true;
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
            //self.flip(true);
            boolean move = true;
            for (GameObject i : ObjectRenderer.getTiles()) {
                if (!i.isIgnore()) {
                    if (self.isTouchingEast(i) && !self.isTouchingNorth(i)) {
                        move = false;
                        break;
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
            //self.flip(false);
            boolean move = true;
            for (GameObject i : ObjectRenderer.getTiles()) {
                if (!i.isIgnore()) {
                    if (self.isTouchingWest(i) && !self.isTouchingNorth(i)) {
                        move = false;
                        break;
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
            if (canJump) {
                self.moveSpritePosBy(0.0,0.025);
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        canJump = false;
                    }
                }, 300);
            }
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
