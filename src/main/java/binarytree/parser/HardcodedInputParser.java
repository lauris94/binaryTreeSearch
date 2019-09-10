package binarytree.parser;

import binarytree.model.Node;
import binarytree.model.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class HardcodedInputParser implements InputParser {

    @Override
    public Tree parseTree() {
        List<List<Node>> nodes = new ArrayList<>();

        nodes.add(createNodes(0, new int[]{1}));
        nodes.add(createNodes(1, new int[]{8, 9}));
        nodes.add(createNodes(2, new int[]{1, 5, 9}));
        nodes.add(createNodes(3, new int[]{4, 5, 2, 3}));

        return new Tree(nodes);
    }

    private List<Node> createNodes(int rowNumber, int[] values) {
        return IntStream.range(0, values.length)
                .mapToObj(columnNumber -> new Node(values[columnNumber], rowNumber, columnNumber))
                .collect(toList());
    }
}
