package bsu.frct.java.lab3;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends Frame {
    private static final int WIDTH = 1000,
                             HEIGHT = 500;
    private Double[] coefficients;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem,
                      saveToGraphicsMenuItem,
                      saveToCSVMenuItem,
                      searchValueMenuItem,
                      searchSimilarValueMenuItem;
    private JTextField textFieldFrom,
                       textFieldTo,
                       textFieldStep;
    private Box hBoxResult;
    private HornerTableCellRenderer renderer = new HornerTableCellRenderer();
    private HornerTableModel data;

    public static void main(String[] args) {

    }
}
