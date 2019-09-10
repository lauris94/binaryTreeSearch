package binarytree;

import binarytree.model.Route;
import binarytree.model.Tree;
import binarytree.parser.HardcodedInputParser;
import binarytree.parser.InputParser;
import binarytree.service.RouteFinderService;

import java.util.List;

import static binarytree.utils.RouteUtils.calculateRoutesSum;

public class Main {

    public static void main(String[] args) {
        //InputParser inputParser = new FileInputParser("input.txt");
        InputParser inputParser = new HardcodedInputParser();

        RouteFinderService routeFinderService = new RouteFinderService();

        Tree tree = inputParser.parseTree();

        List<Route> allRoutes = routeFinderService.findAllRoutesForTree(tree);

        calculateRoutesSum(allRoutes);

        Route bestRoute = routeFinderService.findBestRoute(allRoutes);

        System.out.println(String.format("Max sum: %d", bestRoute.getSum()));
        System.out.println(String.format("Path: %s", bestRoute.getPath()));
    }
}
