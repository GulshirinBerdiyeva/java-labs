package bsu.frct.java.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1000,
                             HEIGHT = 500;
    private Double[] coefficients;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem,
                      saveToGraphicsMenuItem,
                      searchValueMenuItem;
    private JTextField textFieldFrom,
                       textFieldTo,
                       textFieldStep;
    private Box hBoxResult;
    private HornerTableCellRenderer renderer = new HornerTableCellRenderer();
    private HornerTableModel data;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public MainFrame(Double[] coefficients){
        super("Tabulating a polynomial on a segment in 2 ways");

        this.coefficients = coefficients;

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Table");
        menuBar.add(tableMenu);

        Action saveToTextAction = new AbstractAction("Save to text file") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser == null){
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(""));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) ==
                        JFileChooser.APPROVE_OPTION){
                    saveToTextFile(fileChooser.getSelectedFile());
                }
            }
        };
        saveToTextMenuItem = fileMenu.add(saveToTextAction);
        saveToTextMenuItem.setEnabled(false);

        Action saveToGraphicsAction = new AbstractAction("Save data for plotting") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser == null){
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(""));
                }
                if (fileChooser.showSaveDialog(MainFrame.this) ==
                    JFileChooser.APPROVE_OPTION){
                    saveToGraphicsFile(fileChooser.getSelectedFile());
                }
            }
        };
        saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        saveToGraphicsMenuItem.setEnabled(false);

        Action searchValueAction = new AbstractAction("Find the value") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(MainFrame.this,
                        "Enter a value to search", "Value search",
                        JOptionPane.QUESTION_MESSAGE);
                renderer.setNeedle(value);
                getContentPane().repaint();
            }
        };
        searchValueMenuItem = tableMenu.add(searchValueAction);
        searchValueMenuItem.setEnabled(false);

        JLabel labelForFrom = new JLabel("X varies from:");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForTo = new JLabel("to:");
        textFieldTo = new JTextField("1.0", 10);
        textFieldTo.setMaximumSize(textFieldFrom.getPreferredSize());
        JLabel labelForStep = new JLabel("with step:");
        textFieldStep = new JTextField("0.1", 10);
        textFieldStep.setMaximumSize(textFieldFrom.getPreferredSize());

        Box hBoxRange = Box.createHorizontalBox();
        hBoxRange.setBorder(BorderFactory.createBevelBorder(1));
        hBoxRange.add(Box.createHorizontalStrut(10));
        hBoxRange.add(labelForFrom);
        hBoxRange.add(Box.createHorizontalStrut(10));
        hBoxRange.add(textFieldFrom);
        hBoxRange.add(Box.createHorizontalGlue());
        hBoxRange.add(labelForTo);
        hBoxRange.add(Box.createHorizontalStrut(10));
        hBoxRange.add(textFieldTo);
        hBoxRange.add(Box.createHorizontalGlue());
        hBoxRange.add(labelForStep);
        hBoxRange.add(Box.createHorizontalStrut(10));
        hBoxRange.add(textFieldStep);
        hBoxRange.add(Box.createHorizontalStrut(10));
        hBoxRange.setPreferredSize(new Dimension(
                new Double(hBoxRange.getMaximumSize().getWidth()).intValue(),
                new Double(hBoxRange.getMinimumSize().getHeight()).intValue() * 2));
        hBoxRange.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        getContentPane().add(hBoxRange, BorderLayout.NORTH);

        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Double from = Double.parseDouble(textFieldFrom.getText()),
                           to = Double.parseDouble(textFieldTo.getText()),
                           step = Double.parseDouble(textFieldStep.getText());
                    data = new HornerTableModel(from, to, step, MainFrame.this.coefficients);
                    JTable table = new JTable(data);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);
                    hBoxResult.removeAll();
                    hBoxResult.add(new JScrollPane(table));
                    getContentPane().validate();
                    saveToTextMenuItem.setEnabled(true);
                    saveToGraphicsMenuItem.setEnabled(true);
                    searchValueMenuItem.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                                                  "Floating point format error", "Wrong number format",
                                                  JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");
                hBoxResult.removeAll();
                hBoxResult.add(new JPanel());
                saveToTextMenuItem.setEnabled(false);
                saveToGraphicsMenuItem.setEnabled(false);
                searchValueMenuItem.setEnabled(false);
                getContentPane().validate();
            }
        });

        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(50));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setPreferredSize(new Dimension(
                new Double(hBoxButtons.getMaximumSize().getWidth()).intValue(),
                new Double(hBoxButtons.getMinimumSize().getHeight()).intValue() * 2));
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        getContentPane().add(hBoxButtons, BorderLayout.SOUTH);
        hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(new JPanel());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        getContentPane().add(hBoxResult, BorderLayout.CENTER);
    }

    private void saveToGraphicsFile(File selectedFile) {
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
            for (int i = 0; i < data.getRowCount(); i++){
                for (int j = 0; j < data.getColumnCount(); j++) {
                    out.writeDouble((Double)data.getValueAt(i, j));
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("File couldn't be created");
        }
    }
    public void saveToTextFile(File selectedFile) {
        try {
            FileWriter writer = new FileWriter(selectedFile);
            for (int i = 0; i < data.getColumnCount(); i++){
                writer.write(data.getColumnName(i));
                writer.append("\t|\t");
            }

            writer.append("\n___________________________________________________________" +
                          "______________________________________________________________\n");

            for (int i = 0; i < data.getRowCount(); i++){
                for (int j = 0; j < data.getColumnCount(); j++){
                    writer.write(String.valueOf(formatter.format(data.getValueAt(i,j))));
                    int a = formatter.format(data.getValueAt(i,j)).toString().length();
                    for (int l = 1; l < (30 - a); l++) {
                        writer.append(" ");
                    }
                }
                writer.append("\n");
            }
            writer.flush();
        }catch (IOException e){
            System.out.println("File couldn't be created");
        }
    }

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("It is impossible to tabulate a polynomial for which no coefficient is given!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[args.length];
        int i = 0;
        try{
            for(String arg : args){
                coefficients[i++] = Double.parseDouble(arg);
            }
        }catch (NumberFormatException e){
            System.out.println("Error converting a string '" + args[i] + "' to a Double");
            System.exit(-2);
        }
        MainFrame frame = new MainFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}