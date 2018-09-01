package UI;

import Utility.GLInstruct;
import Utility.GameObject;
import Utility.QuickDraw;
import Utility.Sprite;

import javax.media.opengl.GLAutoDrawable;

public class Healthbar extends GameObject {

    private double percent;

    public Healthbar(final Sprite center) {
        super("Health bar - " + center.getIdentifier(), false,true, center.getxPos(), center.getyPos() + 0.01,
                center.getxBound(), center.getyBound(), new GLInstruct() {
                    public void instruct(GLAutoDrawable glAutoDrawable) {

                    }
                });
        percent = 1.0;
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {
                QuickDraw.drawHealthLine(percent,center,glAutoDrawable);
            }
        });
    }

    public Healthbar() {
        super("", true, true, -1.0, 1.0, 0, 0, new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {

            }
        });
        percent = 1.0;
    }

    public void clear() {
        super.setRenderInstructions(new GLInstruct() {
            public void instruct(GLAutoDrawable glAutoDrawable) {

            }
        });
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void changePercent(double amnt) {
        percent += amnt;
    }
}
