package Utility;

import Rendering.ObjectRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sprite extends GameObject {

    private File charSprite;
    private double xPos;
    private double yPos;
    private double height;
    private double width;

    public Sprite(String identity, final double x, final double y, final double w, final double h, final File spriteImage) {
        super(identity,false, x, y, w, h, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) { }
        });
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                GL2 tempGL = glAutoDrawable.getGL().getGL2();
                try {
                    Texture texture = TextureIO.newTexture(spriteImage, true);
                    texture.enable(tempGL);
                    texture.bind(tempGL);

                    tempGL.glBegin(tempGL.GL_POLYGON);
                    tempGL.glTexCoord2d(0.0,0.0);
                    tempGL.glVertex2d(xPos,yPos);
                    tempGL.glTexCoord2d(1.0,0.0);
                    tempGL.glVertex2d(xPos + w, yPos);
                    tempGL.glTexCoord2d(1.0,1.0);
                    tempGL.glVertex2d(xPos + w, yPos + h);
                    tempGL.glTexCoord2d(0.0,1.0);
                    tempGL.glVertex2d(xPos,yPos + h);

                    tempGL.glEnd();
                    tempGL.glFlush();
                } catch (IOException e) {
                    System.err.println("File not found for sprite: " + spriteImage.getName());
                    System.exit(-1);
                }
            }
        });
        charSprite = spriteImage;
    }

    public File getCharSprite() {
        return charSprite;
    }

    public void setCharSprite(File charSprite) {
        this.charSprite = charSprite;
    }

    public void moveSpritePosBy(double x, double y) {
        super.movePosBy(x,y);
        xPos += x;
        yPos += y;
    }

    public void moveSpritePosTo(double x, double y) {
        super.movePosTo(x,y);
        xPos = x;
        yPos = y;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }


    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
