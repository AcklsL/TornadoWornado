package NPC.AI;

import Rendering.ObjectRenderer;
import Utility.GameObject;
import Utility.HeldItem;
import Utility.Sprite;

public class PlayerAI extends BaseAI {

    Sprite character;
    private static double fallSpeed = 0.01;
    private static double moveSpeed = 0.025;

    public PlayerAI() {
    }

    public void supplySprite(Sprite in) {
        character = in;
    }

    public void nextMove() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onLeftMovement() {
        try {
            boolean move = true;
            for (GameObject i : ObjectRenderer.getImages()) {
                if (i != character && !(i instanceof HeldItem)) {
                    if (character.isTouchingEast(i) && !character.isTouchingNorth(i)) {
                        move = false;
                    }
                }
            }
            if (move) {
                for (GameObject i : ObjectRenderer.getImages()) {
                    if (i != character) {
                        if (i instanceof Sprite) {
                            ((Sprite) i).moveSpritePosBy(moveSpeed,0.0);
                        } else {
                            i.movePosBy(moveSpeed, 0.0);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onRightMovement() {
        try {
            boolean move = true;
            for (GameObject i : ObjectRenderer.getImages()) {
                if (i != character && !(i instanceof HeldItem)) {
                    if (character.isTouchingWest(i) && !character.isTouchingNorth(i)) {
                        move = false;
                    }
                }
            }
            if (move) {
                for (GameObject i : ObjectRenderer.getImages()) {
                    if (i != character) {
                        if (i instanceof Sprite) {
                            ((Sprite) i).moveSpritePosBy(-moveSpeed,0.0);
                        } else {
                            i.movePosBy(-moveSpeed, 0.0);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onJumpMovement() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onDownMovement() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onPrimaryAttack() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onSecondaryAttack() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onAttacked() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }

    public void onHealthGain() {
        try {

        } catch (NullPointerException e) {
            System.err.println("A Sprite has not been supplied to the PlayerAI!");
        }
    }
}
