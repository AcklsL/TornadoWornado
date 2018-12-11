package Rendering;

import java.io.File;

public class FileHolder {
    public static File bon = new File("./src/main/java/Assets/bon.jpg");
    public static File healthBarGrey = new File("./src/main/java/Assets/healthBarGrey.png");
    public static File manaBar = new File("./src/main/java/Assets/manaBar.png");
    public static File nou = new File("./src/main/java/Assets/nou.png");
    public static File statusBar = new File("./src/main/java/Assets/statusBar.png");
    public static File greyBack = new File("./src/main/java/Assets/healthBarGrey.png");
    public static File redFront = new File("./src/main/java/Assets/healthBarRed.png");

    public static File getBon() {
        return bon;
    }

    public static File getHealthBarGrey() {
        return healthBarGrey;
    }

    public static File getManaBar() {
        return manaBar;
    }

    public static File getNou() {
        return nou;
    }

    public static File getStatusBar() {
        return statusBar;
    }

    public static File getGreyBack() {
        return greyBack;
    }

    public static File getRedFront() {
        return redFront;
    }
}
