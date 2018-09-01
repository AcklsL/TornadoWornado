import KeyBinds.*;
import Maps.DefaultMap;
import Maps.ObjectMap;
import NPC.AI.BaseAI;
import NPC.AI.BasicMeleeAI;
import NPC.AI.NoAI;
import NPC.AI.PlayerAI;
import Rendering.ObjectRenderer;
import Utility.GameObject;
import Utility.HeldItem;
import Utility.Sprite;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StartGame {

    private static GLCanvas canvas;
    private static JFrame frame;
    private static FPSAnimator animator;
    private static KeyFinder listener;
    private static ObjectRenderer renderer;
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
    private static Sprite testSprite;
    private static PlayerAI playerAI;
    private static ObjectMap currentMap;

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
        renderer = new ObjectRenderer();
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
                        new File("./src/main/java/Assets/nou.png"), new onHit() {
                    public void onHitAction(Weapon in) {
                        System.out.println(in.getHolder().getIdentifier() + " struck character for " + in.getDamage() + " damage with " + in.getName());
                    }
                }, playerAI);
                testSprite = new Sprite("testSprite", 100, -0.5, 0.0, 0.125, 0.125,
                        new File("./src/main/java/Assets/nou.png"), new onHit() {
                    public void onHitAction(Weapon in) {
                        System.out.println(in.getHolder().getIdentifier() + " struck testSprite for " + in.getDamage() + " damage with " + in.getName());
                    }
                }, new BasicMeleeAI(character));
                playerAI.supplySprite(character);
                testSprite.getAI().supplySprite(testSprite);
                test = new Iron_Shortsword(character);
                currentMap = new DefaultMap();
                renderer.addObjectToScreen(character);
                renderer.addObjectToScreen(testSprite);
                renderer.addObjectToScreen(test.getImage());
                renderer.addMapToScreen(currentMap);
                renderer.createInstruct();
            }

            /**
             * Ran at close of program
             */
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            /**
             * Ran at beginning and also when it is repainted.
             */
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2();
                gl.glLoadIdentity();
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
                if (keys != null) keyTest();
                physics();
                for (Sprite i : ObjectRenderer.getSprites()) {
                    i.getAI().nextMove();
                }
                renderer.getInstruct().instruct(glAutoDrawable);
            }

            /**
             * Ran when window first shows up, and when window is resized.
             */
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

            }
        });

        animator.start();
        frame.setVisible(true);
    }

    private static void keyTest() {
        mouseListener.refreshMouseLocation();
        if (mouseListener.isLMB()) {
            System.out.println("LMB");
            if (character.holdingItem()) {
                character.getItem().onLeftClick();
            }
        }

        if (mouseListener.isMMB()) {
            System.out.println("MMB");
            if (character.holdingItem()) {
                character.getItem().onMiddleClick();
            }
        }

        if (mouseListener.isRMB()) {
            System.out.println("RMB");
            if (character.holdingItem()) {
                character.getItem().onRightClick();
            }
        }

        //if (listener.containsKey(KeyEvent.VK_W)) { }

        if (listener.containsKey(KeyEvent.VK_A)) {
            character.getAI().onLeftMovement();
        }

        if (listener.containsKey(KeyEvent.VK_S)) {
            character.getAI().onDownMovement();
        }

        if (listener.containsKey(KeyEvent.VK_D)) {
            character.getAI().onRightMovement();
        }

        if (listener.containsKey(KeyEvent.VK_SPACE)) {
            for (GameObject i : ObjectRenderer.getImages()) {
                if (canJump && !character.isTouchingSouth(i) && i != character) {
                    character.moveSpritePosBy(0.0,0.0025);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            canJump = false;
                        }
                    }, 250);
                }
            }
        }

        for (GameObject i : ObjectRenderer.getImages()) {
            if (i != character && character.isTouchingSouth(i)) {
                canJump = false;
                break;
            }
        }
    }

    private static void physics() {
        ArrayList<Sprite> toFall = new ArrayList<Sprite>();
        for (Sprite sprite : ObjectRenderer.getSprites()) {
            toFall.add(sprite);
            for (GameObject object : ObjectRenderer.getImages()) {
                if (sprite != object && !(object instanceof HeldItem)) {
                    if (sprite.isTouchingNorth(object)) {
                        toFall.remove(sprite);
                        canJump = true;
                        break;
                    } else if (!(sprite.getyPos() > -1.0)) {
                        toFall.remove(sprite);
                        canJump = true;
                        break;
                    }
                }
            }
        }
        for (Sprite sprite : toFall) {
            sprite.moveSpritePosBy(0.0, -0.01);
        }
    }

    public static Sprite getCharacter() {
         return character;
    }
}
