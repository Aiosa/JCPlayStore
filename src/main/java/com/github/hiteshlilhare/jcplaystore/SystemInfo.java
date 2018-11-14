package com.github.hiteshlilhare.jcplaystore;

import java.awt.*;

/**
 * @author Jiří Horák
 * @version 1.0
 */
public class SystemInfo {

    public static double getScreenWidth() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.getWidth();
    }

    public static double getScreenHeight() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.getHeight();
    }
}
