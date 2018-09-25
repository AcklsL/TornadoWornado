package NPC.AI;

import Rendering.ObjectRenderer;
import Rendering.GameObject;
import Rendering.Projectile;
import Rendering.Sprite;

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
                if (!i.isIgnore() && i != character && !(i instanceof Projectile)) {
                    if (character.isTouchingEast(i) && !character.isTouchingNorth(i)) {
                        move = false;
                        break;
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
                if (!i.isIgnore() && i != character && !(i instanceof Projectile)) {
                    if (character.isTouchingWest(i) && !character.isTouchingNorth(i)) {
                        move = false;
                        break;
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
