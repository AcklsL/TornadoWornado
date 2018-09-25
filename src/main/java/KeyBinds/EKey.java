package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EKey extends AbstractAction {

    private ArrayList<Integer> events;

    public EKey(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        if (!events.contains(KeyEvent.VK_E)) {
            events.add(KeyEvent.VK_E);
        }
    }

    
}
