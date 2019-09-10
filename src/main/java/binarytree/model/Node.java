package binarytree.model;

public class Node {

    private int value;
    private Coordinates coordinates;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, int rowNumber, int columnNumber) {
        this.value = value;
        this.coordinates = new Coordinates(rowNumber, columnNumber);
    }

    public int getValue() {
        return value;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
