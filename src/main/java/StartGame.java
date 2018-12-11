import Abilities.AbilityBase;
import KeyBinds.*;
import Maps.DefaultMap;
import Maps.ObjectMap;
import NPC.AI.BasicMeleeAI;
import NPC.AI.PlayerAI;
import Rendering.*;
import UI.CharacterStatusBar;
import Utility.GLInstruct;
import Utility.onHit;
import Weapons.Melee.Iron_Shortsword;
import Weapons.Weapon;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StartGame {

    private static GLCanvas canvas;
    private static JFrame frame;
    private static FPSAnimator animator;
    private static KeyFinder listener;
    private static MouseEvents mouseListener;

    private static final int canvasX = 500;
    private static final int canvasY = 500;
    private static final String canvasTitle = "Tornado Wornado";
    private static int fps = 60; //Not actual fps
    private static Timer timer;
    private static boolean canJump;
    private static boolean inAir;

    private static Iron_Shortsword test;

    private static Sprite character;
    private static PlayerAI playerAI;
    private static ObjectMap currentMap;

    private static CharacterStatusBar statusBar;

    private static ArrayList<Integer> keys;

    public static void main(String[] args) {
        init();
    }

    /**
     * Ran at beginning of program to initialize everything.
     */
     private static void init() {
        frame = new JFrame(canvasTitle);
        canvas = new GLCanvas();
        animator = new FPSAnimator(canvas, fps);
        keys = new ArrayList<Integer>();
        listener = new KeyFinder(keys);
        canJump = true;
        timer = new Timer();
        mouseListener = new MouseEvents();
        playerAI = new PlayerAI();

        frame.getContentPane().add(listener);
        frame.getContentPane().add(canvas);
        canvas.addMouseListener(mouseListener);

        {
            //W Key
            listener.getInputMap().put(KeyStroke.getKeyStroke('w'), "W key");
            listener.getActionMap().put("W key", new WKey(keys));

            //A Key
            listener.getInputMap().put(KeyStroke.getKeyStroke('a'), "A key");
            listener.getActionMap().put("A key", new AKey(keys));

            //S Key
            listener.getInputMap().put(KeyStroke.getKeyStroke('s'), "S key");
            listener.getActionMap().put("S key", new SKey(keys));

            //D key
            listener.getInputMap().put(KeyStroke.getKeyStroke('d'), "D key");
            listener.getActionMap().put("D key", new DKey(keys));

            //Space key
            listener.getInputMap().put(KeyStroke.getKeyStroke(' '), "Space key");
            listener.getActionMap().put("Space key", new SpaceKey(keys));

            //Q key
            listener.getInputMap().put(KeyStroke.getKeyStroke('q'), "Q key");
            listener.getActionMap().put("Q key", new QKey(keys));

            //E key
            listener.getInputMap().put(KeyStroke.getKeyStroke('e'), "E key");
            listener.getActionMap().put("E key", new EKey(keys));

            //W key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released W"), "W key release");
            listener.getActionMap().put("W key release", new WKeyR(keys));

            //A key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released A"), "A key release");
            listener.getActionMap().put("A key release", new AKeyR(keys));

            //S key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released S"), "S key release");
            listener.getActionMap().put("S key release", new SKeyR(keys));

            //D key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released D"), "D key release");
            listener.getActionMap().put("D key release", new DKeyR(keys));

            //Space key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "Space key release");
            listener.getActionMap().put("Space key release", new SpaceKeyR(keys));

            //Q key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released Q"), "Q key release");
            listener.getActionMap().put("Q key release", new QKeyR(keys));

            //E key released
            listener.getInputMap().put(KeyStroke.getKeyStroke("released E"), "E key release");
            listener.getActionMap().put("E key release", new EKeyR(keys));
        }

        canvas.setFocusable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new Thread() {
                    @Override
                    public void run() {
                        if (animator.isStarted()) {
                            animator.stop();
                            System.exit(0);
                        }
                    }
                }.start();
            }
        });
        frame.setSize(new Dimension(500,500));

        canvas.addGLEventListener(new GLEventListener() {
            /**
             * Ran at the start of program
             */
            public void init(GLAutoDrawable glAutoDrawable) {
                character = new Sprite("Character", 100, 0.0, 0.5, 0.125, 0.125,
                        FileHolder.getBon(), new onHit() {
                    public void onHitAction(Weapon in) {
                        System.out.println(in.getHolder().getIdentifier() + " struck character for " + in.getDamage() + " damage with " + in.getName());
                    }
                }, playerAI);
                playerAI.supplySprite(character);
                test = new Iron_Shortsword(character);
                currentMap = new DefaultMap();
                ObjectRenderer.addObjectToScreen(character);
                ObjectRenderer.addObjectToScreen(test.getImage());
                ObjectRenderer.addMapToScreen(currentMap);
                statusBar = new CharacterStatusBar();
                ObjectRenderer.addOverlay(statusBar);
                ObjectRenderer.createInstruct();
            }

            /**
             * Ran at close of program
             */
            public void dispose(GLAutoDrawable glAutoDrawable) {
                ObjectRenderer.setInstruct(new GLInstruct() {
                    public void instruct(GLAutoDrawable glAutoDrawable) {

                    }
                });
            }

            /**
             * Ran at beginning and also when it is repainted.
             */
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2(); //Intiator
                gl.glLoadIdentity(); //Reset screen.
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT); //Reset screen.
                if (keys != null && !keys.isEmpty()) keyTest();
                physics();
                for (Sprite i : ObjectRenderer.getSprites()) {
                    i.getAI().nextMove(); //See BaseAI
                }
                for (Projectile i : ObjectRenderer.getProjectiles()) {
                    i.move();
                }
                ObjectRenderer.getAbilityQ().cooldownUpdate();
                ObjectRenderer.getAbilityE().cooldownUpdate();
                ObjectRenderer.filter();
                ObjectRenderer.getInstruct().instruct(glAutoDrawable);
                character.changeMana(0.13);
            }

            /**
             * Ran when window first shows up, and when window is resized.
             */
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

            }
        });

        //Required items. don't do this then program = screwed.
        animator.start();
        frame.setVisible(true);
    }

    /**
     * Method run at every frame to test for key input.
     * Used only for character and player-based actions.
     */
    private static void keyTest() {
        //not gonna explain mouseListener. its obvious
        if (mouseListener.isLMB()) {
            mouseListener.refreshMouseLocation();
            if (character.holdingItem()) {
                character.getItem().onLeftClick();
            }
        }

        if (mouseListener.isMMB()) {
            mouseListener.refreshMouseLocation();
            if (character.holdingItem()) {
                character.getItem().onMiddleClick();
            }
        }

        if (mouseListener.isRMB()) {
            mouseListener.refreshMouseLocation();
            if (character.holdingItem()) {
                character.getItem().onRightClick();
            }
        }

        if (listener.containsKey(KeyEvent.VK_Q)) {
            ObjectRenderer.getAbilityQ().getEffectInstructions().instruct();
        }

        if (listener.containsKey(KeyEvent.VK_E)) {
            ObjectRenderer.getAbilityE().getEffectInstructions().instruct();
        }

        //if (listener.containsKey(KeyEvent.VK_W)) { }

        if (listener.containsKey(KeyEvent.VK_A)) {
            character.getAI().onLeftMovement();
            character.flip(true);
        }

        if (listener.containsKey(KeyEvent.VK_S)) {
            character.getAI().onDownMovement();
        }

        if (listener.containsKey(KeyEvent.VK_D)) {
            character.getAI().onRightMovement();
            character.flip(false);
        }

        //Ensures that you can't jump into something from the bottom.
        for (GameObject i : ObjectRenderer.getImages()) {
            if (i != character && !(i instanceof Sprite) && !(i instanceof Projectile)
                    && (character.isTouchingSouth(i) && !(character.isTouchingEast(i) || character.isTouchingWest(i)))) {
                canJump = false;
                break;
            }
        }

        //Does the jump action.
        if (listener.containsKey(KeyEvent.VK_SPACE)) {
            if (canJump) {
                character.moveSpritePosBy(0.0,0.03);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        canJump = false;
                    }
                }, 300);
            }
        }


    }

    /**
     * The physics method run every frame.
     * Affects ALL SPRITES!
     */
    private static void physics() {
        ArrayList<Sprite> toFall = new ArrayList<Sprite>(); //List of sprites that will fall.
        for (Sprite sprite : ObjectRenderer.getSprites()) {
            toFall.add(sprite); //Adds all the sprites to above list.
            for (GameObject object : ObjectRenderer.getImages()) {
                if (sprite != object && !object.isIgnore() && !(object instanceof Projectile)) { //This line prevents self-checking. See Objectrenderer for more info
                    if (sprite.isTouchingNorth(object)) { //If it is touching the north side of an object
                        toFall.remove(sprite);
                        if (sprite == character) {
                            canJump = true;
                        }
                        break;
                    } else if (!(sprite.getyPos() > -1.0)) { //If it is on the bottom of the screen.
                        toFall.remove(sprite);
                        if (sprite == character) {
                            canJump = true;
                        }
                        break;
                    }
                }
            }
        }
        for (Sprite sprite : toFall) {
            sprite.moveSpritePosBy(0.0, -0.01); //The actual falling of the sprite.
        }
    }

    /**
     * Returns the character sprite
     * @return Sprite
     */
    public static Sprite getCharacter() {
         return character;
    }
}
