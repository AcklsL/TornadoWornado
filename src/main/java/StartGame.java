import KeyBinds.*;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class StartGame {

    private static GLCanvas canvas;
    private static JFrame frame;
    private static FPSAnimator animator;
    private static KeyFinder listener;

    private static final int canvasX = 500;
    private static final int canvasY = 500;
    private static final String canvasTitle = "Tornado Wornado";
    private static int fps = 60; //Not actual fps
    private static double testx = 0.0;
    private static double testy = 0.0;

    protected static ArrayList<Integer> keys;

    public static void main(String[] args) {
        init();
    }

    /**
     * Ran at beginning of program to initialize everything.
     */
    public static void init() {
        frame = new JFrame(canvasTitle);
        canvas = new GLCanvas();
        animator = new FPSAnimator(canvas, fps);
        keys = new ArrayList<Integer>();
        listener = new KeyFinder(keys);

        frame.getContentPane().add(listener);
        frame.getContentPane().add(canvas);

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
             * @param glAutoDrawable
             */
            public void init(GLAutoDrawable glAutoDrawable) {

            }

            /**
             * Ran at close of program
             * @param glAutoDrawable
             */
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            /**
             * Ran at beginning and also when it is repainted.
             * @param glAutoDrawable
             */
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2();
                gl.glLoadIdentity();
                gl.glClear(GL.GL_COLOR_BUFFER_BIT);
                if (keys != null) {
                    keyTest();
                }
                {
                    gl.glBegin(GL.GL_TRIANGLES);
                    gl.glVertex2d(testx - 0.5,testy);
                    gl.glVertex2d(testx + 0.5,testy);
                    gl.glVertex2d(testx,testy + 0.5);
                    gl.glEnd();
                }
            }

            /**
             * Ran when window first shows up, and when window is resized.
             * @param glAutoDrawable
             * @param i
             * @param i1
             * @param i2
             * @param i3
             */
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

            }
        });

        animator.start();
        frame.setVisible(true);
    }

    private static void keyTest() {
        if (listener.containsKey(KeyEvent.VK_W)) {
            testy += 0.01;
        }

        if (listener.containsKey(KeyEvent.VK_A)) {
            testx -= 0.01;
        }

        if (listener.containsKey(KeyEvent.VK_S)) {
            testy -= 0.01;
        }

        if (listener.containsKey(KeyEvent.VK_D)) {
            testx += 0.01;
        }

        if (listener.containsKey(KeyEvent.VK_SPACE)) {

        }
    }
}
