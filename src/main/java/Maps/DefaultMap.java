package Maps;

import Utility.GLInstruct;
import Utility.GameObject;
import Utility.Sprite;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultMap extends ObjectMap{

    //private Layer[] mapLayers;
    private ArrayList<GameObject> objects;
    private ArrayList<Sprite> mapSprites;

    private GameObject WestBorder;
    private GameObject SouthBorder;
    private GameObject NorthBorder;
    private GameObject EastBorder;
    private GameObject PlatformA;
    private GameObject PlatformB;

    private final double fallSpeed = 0.01;
    private final double moveSpeed = 0.015;

    public DefaultMap() {
        System.out.println("Map gen start");
        objects = new ArrayList<GameObject>();
        mapSprites = new ArrayList<Sprite>();

        System.out.println("West border gen start");
        WestBorder = new GameObject("West Wall", false, -2.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(WestBorder.getX(),WestBorder.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(WestBorder.getX() + WestBorder.getxBound(), WestBorder.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(WestBorder.getX() + WestBorder.getxBound(), WestBorder.getY() + WestBorder.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(WestBorder.getX(),WestBorder.getY() + WestBorder.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        EastBorder = new GameObject("East Wall", false, 5.0, -1.0, 0.5, 2.0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(EastBorder.getX(),EastBorder.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(EastBorder.getX() + EastBorder.getxBound(), EastBorder.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(EastBorder.getX() + EastBorder.getxBound(), EastBorder.getY() + EastBorder.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(EastBorder.getX(),EastBorder.getY() + EastBorder.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        NorthBorder = new GameObject("North Wall", false, -2.0, 0.8, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(NorthBorder.getX(),NorthBorder.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(NorthBorder.getX() + NorthBorder.getxBound(), NorthBorder.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(NorthBorder.getX() + NorthBorder.getxBound(), NorthBorder.getY() + NorthBorder.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(NorthBorder.getX(),NorthBorder.getY() + NorthBorder.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        SouthBorder = new GameObject("South Wall", false, -2.0, -0.8, 7.0, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(SouthBorder.getX(),SouthBorder.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(SouthBorder.getX() + SouthBorder.getxBound(), SouthBorder.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(SouthBorder.getX() + SouthBorder.getxBound(), SouthBorder.getY() + SouthBorder.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(SouthBorder.getX(),SouthBorder.getY() + SouthBorder.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        PlatformA = new GameObject("Platform A - Default Map", false, 0.2, -0.6, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(PlatformA.getX(),PlatformA.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(PlatformA.getX() + PlatformA.getxBound(), PlatformA.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(PlatformA.getX() + PlatformA.getxBound(), PlatformA.getY() + PlatformA.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(PlatformA.getX(),PlatformA.getY() + PlatformA.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        PlatformB = new GameObject("Platform B - Default Map", false, 0.7, -0.4, 0.4, 0.2, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                File spriteImage = new File("C:\\Users\\Duska\\Documents\\GitHub\\TornadoWornado\\src\\main\\java\\Assets\\bon.jpg");
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(PlatformB.getX(),PlatformB.getY());
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(PlatformB.getX() + PlatformB.getxBound(), PlatformB.getY());
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(PlatformB.getX() + PlatformB.getxBound(), PlatformB.getY() + PlatformB.getyBound());
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(PlatformB.getX(),PlatformB.getY() + PlatformB.getyBound());

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });

        System.out.println("add west border");
        objects.add(WestBorder);
        objects.add(EastBorder);
        objects.add(SouthBorder);
        objects.add(NorthBorder);
        objects.add(PlatformA);
        objects.add(PlatformB);
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void addToMap(GameObject toAdd) {
        objects.add(toAdd);
    }

    public void addSprite(Sprite toAdd) {
        mapSprites.add(toAdd);
    }

    public ArrayList<GameObject> getMap() {
        return objects;
    }

    public ArrayList<Sprite> getMapSprites() {
        return mapSprites;
    }
}
