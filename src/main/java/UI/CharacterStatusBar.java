package UI;

import Rendering.ObjectRenderer;
import Utility.GLInstruct;
import Utility.QuickDraw;
import Rendering.Sprite;

import javax.media.opengl.GLAutoDrawable;
import java.io.File;

public class CharacterStatusBar extends Overlay{

    private Sprite character;

    public CharacterStatusBar() {
        super("Character status bar", new File("./src/main/java/Assets/statusBar.png"), -1.0, 0.8, 0.5, 0.25);
        for (Sprite i : ObjectRenderer.getSprites()) {
            if (i.getIdentifier().equalsIgnoreCase("Character")) {
                character = i;
                break;
            }
        }
        super.setInstruct(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.quickTexture(new File("./src/main/java/Assets/statusBar.png"), false,  -0.5, -1.0, 1.0, 0.2, glAutoDrawable);
                QuickDraw.drawHealthLine(character.getHealth() / character.getMaxHealth(), -0.3, -0.88, 0.6, 0.05, glAutoDrawable);
                QuickDraw.drawPercentageBar(new File("./src/main/java/Assets/manaBar.png"), character.getMana() / character.getMaxMana(), -0.3, -0.98, 0.6, 0.05, glAutoDrawable);
            }
        });
    }
}
