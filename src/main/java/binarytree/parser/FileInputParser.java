package binarytree.parser;

import binarytree.model.Node;
import binarytree.model.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static binarytree.utils.FileUtils.getFileLinesFromResources;
import static java.util.stream.Collectors.toList;

public class FileInputParser implements InputParser {

    private static final String DELIMITER = "\\s+";

    private final String filename;

    public FileInputParser(String filename) {
        this.filename = filename;
    }

    @Override
    public Tree parseTree() {
        List<List<Node>> nodes = new ArrayList<>();
        int currentLine = 0;

        for (String line : getFileLinesFromResources(filename)) {
            int[] numbers = parseNumbersFromLine(line);
            nodes.add(createNodesList(currentLine, numbers));
            currentLine++;
        }
        return new Tree(nodes);
    }

    private List<Node> createNodesList(int rowNumber, int[] values) {
        return IntStream.range(0, values.length)
                .mapToObj(columnNumber -> new Node(values[columnNumber], rowNumber, columnNumber))
                .collect(toList());
    }

    private int[] parseNumbersFromLine(String line) {
        String[] items = line.trim().split(DELIMITER);
        return Arrays.stream(items)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
