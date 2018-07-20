package KeyBinds;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {

    private boolean LMB;
    private boolean RMB;
    private boolean MMB;

    private int mouseX;
    private int mouseY;

    public MouseEvents() {
        LMB = false;
        RMB = false;
        MMB = false;
        mouseX = 0;
        mouseY = 0;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            LMB = true;
            mouseX = e.getX();
            mouseY = e.getY();
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            MMB = true;
            mouseX = e.getX();
            mouseY = e.getY();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            RMB = true;
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            LMB = false;
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            MMB = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            RMB = false;
        }
    }

    public void refreshMouseLocation() {
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public boolean isLMB() {
        return LMB;
    }

    public boolean isRMB() {
        return RMB;
    }

    public boolean isMMB() {
        return MMB;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
