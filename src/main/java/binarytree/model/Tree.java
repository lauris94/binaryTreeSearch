package binarytree.model;

import java.util.List;

public class Tree {

    private List<List<Node>> nodes;
    private Node head;

    public Tree(List<List<Node>> nodes) {
        this.nodes = nodes;
        this.head = nodes.get(0).get(0);
    }

    public Node getHead() {
        return head;
    }

    public List<Node> getRow(int rowNumber) {
        return nodes.get(rowNumber);
    }

    public int size() {
        return nodes.size();
    }
}
