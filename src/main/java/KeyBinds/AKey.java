package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AKey extends AbstractAction {

    private ArrayList<Integer> events;

    public AKey(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        if (!events.contains(KeyEvent.VK_A)) {
            events.add(KeyEvent.VK_A);
        }
    }

    
}
