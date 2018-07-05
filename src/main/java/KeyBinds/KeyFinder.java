package KeyBinds;

import javax.swing.*;
import java.util.ArrayList;

public class KeyFinder extends JComponent {

    private ArrayList<Integer> key;

    public KeyFinder(ArrayList<Integer> keys) {
        key = keys;
    }

    public boolean containsKey(int keyFind) {
        return key.contains(keyFind);
    }
}
