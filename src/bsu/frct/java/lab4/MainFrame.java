package bsu.frct.java.lab4;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1000,
                             HEIGHT = 500;
    private boolean fileLoaded = false;
    private GraphicsDisplay display = new GraphicsDisplay();
    private JCheckBoxMenuItem showAxisMenuItem,
                              showMarkerMenuItem;
    private JFileChooser fileChooser = null;

    public static void main(String[] args) {

    }

}
