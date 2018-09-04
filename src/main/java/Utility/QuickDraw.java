package Utility;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QuickDraw {
    public static void quickTexture(File spriteImage, boolean isFlipped, GameObject object, GLAutoDrawable glAutoDrawable) {
        GL2 tempGL = glAutoDrawable.getGL().getGL2();
        try {
            Texture text = TextureIO.newTexture(spriteImage, true);
            text.enable(tempGL);
            text.bind(tempGL);

            TextureCoords coords = text.getImageTexCoords();
            tempGL.glBegin(tempGL.GL_POLYGON);
            if (isFlipped) {
                tempGL.glTexCoord2d(coords.right(),coords.bottom());
                tempGL.glVertex2d(object.getX(),object.getY());
                tempGL.glTexCoord2d(coords.left(),coords.bottom());
                tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY());
                tempGL.glTexCoord2d(coords.left(),coords.top());
                tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY() + object.getyBound());
                tempGL.glTexCoord2d(coords.right(),coords.top());
                tempGL.glVertex2d(object.getX(),object.getY() + object.getyBound());
            } else {
                tempGL.glTexCoord2d(coords.left(),coords.bottom());
                tempGL.glVertex2d(object.getX(),object.getY());
                tempGL.glTexCoord2d(coords.right(),coords.bottom());
                tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY());
                tempGL.glTexCoord2d(coords.right(),coords.top());
                tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY() + object.getyBound());
                tempGL.glTexCoord2d(coords.left(),coords.top());
                tempGL.glVertex2d(object.getX(),object.getY() + object.getyBound());
            }

            tempGL.glEnd();
            tempGL.glFlush();
        } catch (IOException e) {
            System.err.println("File not found for sprite: " + spriteImage.getName());
            System.exit(-1);
        }
    }
    public static void quickTexture(File spriteImage, boolean isFlipped, double x, double y, double w, double h, GLAutoDrawable glAutoDrawable) {
        GL2 tempGL = glAutoDrawable.getGL().getGL2();
        try {
            Texture text = TextureIO.newTexture(spriteImage, true);
            text.enable(tempGL);
            text.bind(tempGL);

            TextureCoords coords = text.getImageTexCoords();
            tempGL.glBegin(tempGL.GL_POLYGON);
            if (isFlipped) {
                tempGL.glTexCoord2d(coords.right(),coords.bottom());
                tempGL.glVertex2d(x,y);
                tempGL.glTexCoord2d(coords.left(),coords.bottom());
                tempGL.glVertex2d(x + w, y);
                tempGL.glTexCoord2d(coords.left(),coords.top());
                tempGL.glVertex2d(x + w, y + h);
                tempGL.glTexCoord2d(coords.right(),coords.top());
                tempGL.glVertex2d(x,y + h);
            } else {
                tempGL.glTexCoord2d(coords.left(),coords.bottom());
                tempGL.glVertex2d(x,y);
                tempGL.glTexCoord2d(coords.right(),coords.bottom());
                tempGL.glVertex2d(x + w, y);
                tempGL.glTexCoord2d(coords.right(),coords.top());
                tempGL.glVertex2d(x + w, y + h);
                tempGL.glTexCoord2d(coords.left(),coords.top());
                tempGL.glVertex2d(x,y + h);
            }
            tempGL.glEnd();
            tempGL.glFlush();
        } catch (IOException e) {
            System.err.println("File not found for sprite: " + spriteImage.getName());
            System.exit(-1);
        }
    }

    /**
     * Draws the health bar line.
     * @param percent must be in decimal percentage. 0.5 for 50%
     * @param glAutoDrawable default
     */
    public static void drawHealthLine(double percent, Sprite center, GLAutoDrawable glAutoDrawable) {
        double xLength = center.getxBound() * percent;
        final File greyBack = new File("./src/main/java/Assets/healthBarGrey.png");
        final File redFront = new File("./src/main/java/Assets/healthBarRed.png");
        QuickDraw.quickTexture(greyBack,false, center.getX(), center.getY() + center.getyBound() + 0.01, center.getxBound(), 0.01, glAutoDrawable);
        QuickDraw.quickTexture(redFront,false, center.getX(), center.getY() + center.getyBound() + 0.01, xLength, 0.01, glAutoDrawable);
    }

    public static void drawHealthLine(double percent, double x, double y, double w, double h, GLAutoDrawable glAutoDrawable) {
        double xLength = w * percent;
        final File greyBack = new File("./src/main/java/Assets/healthBarGrey.png");
        final File redFront = new File("./src/main/java/Assets/healthBarRed.png");
        QuickDraw.quickTexture(greyBack,false, x, y, w, h, glAutoDrawable);
        QuickDraw.quickTexture(redFront,false, x, y, xLength, h, glAutoDrawable);
    }

    public static void drawPercentageBar(File color, double percent, double x, double y, double w, double h, GLAutoDrawable glAutoDrawable) {
        double xLength = w * percent;
        final File greyBack = new File("./src/main/java/Assets/healthBarGrey.png");
        QuickDraw.quickTexture(greyBack,false, x, y, w, h, glAutoDrawable);
        QuickDraw.quickTexture(color,false, x, y, xLength, h, glAutoDrawable);
    }
}
