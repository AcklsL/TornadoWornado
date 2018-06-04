package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SpaceKey extends AbstractAction {

    private ArrayList<Integer> events;

    public SpaceKey(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        if (!events.contains(KeyEvent.VK_SPACE)) {
            events.add(KeyEvent.VK_SPACE);
        }
    }

    
}
