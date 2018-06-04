import javax.imageio.ImageIO;
import javax.media.opengl.GLAutoDrawable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageRenderer {

    private ArrayList<BufferedImage> images;

    public ImageRenderer() {
        images = new ArrayList<BufferedImage>();
    }

    public void addImageToScreen(BufferedImage image) {
        images.add(image);
    }

    public BufferedImage fileToBufferedImage(File in) {
        BufferedImage BImg;
        try {
            BImg = ImageIO.read(in);
            return BImg;
        } catch (IOException e) {
            e.getCause();
        }
        return null;
    }

    public void GLInstruct(GLAutoDrawable glAutoDrawable) {

    }
}
