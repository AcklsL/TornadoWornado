package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class QKey extends AbstractAction {

    private ArrayList<Integer> events;

    public QKey(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        if (!events.contains(KeyEvent.VK_Q)) {
            events.add(KeyEvent.VK_Q);
        }
    }

    
}
