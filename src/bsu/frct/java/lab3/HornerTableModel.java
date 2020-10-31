package bsu.frct.java.lab3;

import javax.swing.table.AbstractTableModel;

public class HornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from,
                   to,
                   step;

    public HornerTableModel(Double from, Double to,
                            Double step, Double[] coefficients){
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "X value";
            case 1:
                return "Horner polynomial value";
            case 2:
                return "Power polynomial value";
            default:
                return "Difference between 2 polynomial values";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }
}
