package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class WKey extends AbstractAction {

    private ArrayList<Integer> events;

    public WKey(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        if (!events.contains(KeyEvent.VK_W)) {
            events.add(KeyEvent.VK_W);
        }
    }


}
