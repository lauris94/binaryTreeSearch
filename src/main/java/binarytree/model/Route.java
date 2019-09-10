package binarytree.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<Node> path = new ArrayList<>();
    private Integer sum;

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Node getLastNode() {
        return path.stream()
                .reduce((node, node2) -> node2)
                .orElse(null);
    }
}
