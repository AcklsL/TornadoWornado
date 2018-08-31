package Utility;

import UI.Healthbar;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import java.io.File;
import java.io.IOException;

public class QuickDraw {
    public static void quickTexture(File spriteImage, GameObject object, GLAutoDrawable glAutoDrawable) {
        GL2 tempGL = glAutoDrawable.getGL().getGL2();
        try {
            Texture text = TextureIO.newTexture(spriteImage, true);
            text.enable(tempGL);
            text.bind(tempGL);

            tempGL.glBegin(tempGL.GL_POLYGON);
            tempGL.glTexCoord2d(0.0,0.0);
            tempGL.glVertex2d(object.getX(),object.getY());
            tempGL.glTexCoord2d(1.0,0.0);
            tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY());
            tempGL.glTexCoord2d(1.0,1.0);
            tempGL.glVertex2d(object.getX() + object.getxBound(), object.getY() + object.getyBound());
            tempGL.glTexCoord2d(0.0,1.0);
            tempGL.glVertex2d(object.getX(),object.getY() + object.getyBound());

            tempGL.glEnd();
            tempGL.glFlush();
        } catch (IOException e) {
            System.err.println("File not found for sprite: " + spriteImage.getName());
            System.exit(-1);
        }
    }
    public static void quickTexture(File spriteImage, double x, double y, double w, double h, GLAutoDrawable glAutoDrawable) {
        GL2 tempGL = glAutoDrawable.getGL().getGL2();
        try {
            Texture text = TextureIO.newTexture(spriteImage, true);
            text.enable(tempGL);
            text.bind(tempGL);

            tempGL.glBegin(tempGL.GL_POLYGON);
            tempGL.glTexCoord2d(0.0,0.0);
            tempGL.glVertex2d(x,y);
            tempGL.glTexCoord2d(1.0,0.0);
            tempGL.glVertex2d(x + w, y);
            tempGL.glTexCoord2d(1.0,1.0);
            tempGL.glVertex2d(x + w, y + h);
            tempGL.glTexCoord2d(0.0,1.0);
            tempGL.glVertex2d(x,y + h);

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
        double xLength = center.getyBound() * percent;
        final File greyBack = new File("./src/main/java/Assets/greyBar.png");
        final File redFront = new File("./src/main/java/Assets/redBar.png");
        QuickDraw.quickTexture(greyBack, center.getX(), center.getY() + center.getyBound() + 0.01, center.getxBound(), 0.02, glAutoDrawable);
        QuickDraw.quickTexture(redFront, center.getX(), center.getY() + center.getyBound() + 0.01, xLength, 0.02, glAutoDrawable);
    }
}
