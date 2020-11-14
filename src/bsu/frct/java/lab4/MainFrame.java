package bsu.frct.java.lab4;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1000,
                             HEIGHT = 500;
    private boolean fileLoaded = false;
    private GraphicsDisplay display = new GraphicsDisplay();
    private JCheckBoxMenuItem showAxisMenuItem,
                              showMarkersMenuItem;
    private JFileChooser fileChooser = null;

    public static void main(String[] args) {

    }

    private class GraphicsMenuListener implements MenuListener{
        @Override
        public void menuSelected(MenuEvent e) {
            showAxisMenuItem.setEnabled(fileLoaded);
            showMarkersMenuItem.setEnabled(fileLoaded);
        }

        @Override
        public void menuDeselected(MenuEvent e) {}

        @Override
        public void menuCanceled(MenuEvent e) {}
    }
}
