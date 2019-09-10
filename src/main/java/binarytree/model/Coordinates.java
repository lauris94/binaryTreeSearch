package binarytree.model;

public class Coordinates {

    private int rowNumber;
    private int columnNumber;

    public Coordinates(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
