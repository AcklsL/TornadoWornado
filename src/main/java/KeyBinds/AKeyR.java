package KeyBinds;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AKeyR extends AbstractAction {

    private ArrayList<Integer> events;

    public AKeyR(ArrayList<Integer> events) {
        this.events = events;
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i) == KeyEvent.VK_A) {
                events.remove(i);
            }
        }
    }

    
}
