package binarytree.service;

import binarytree.model.Node;
import binarytree.model.Route;
import binarytree.model.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RouteFinderService {

    private static final int MAX_CHILDS = 2;

    public List<Route> findAllRoutesForTree(Tree tree) {
        List<Route> allRoutes = initRouteList(tree);
        for (int currentRowIndex = 1; currentRowIndex < tree.size(); currentRowIndex++) {
            List<Node> nodesToCheck = findNodesInRoutesByRow(allRoutes, currentRowIndex - 1);
            List<Node> currentRow = tree.getRow(currentRowIndex);
            updateRoutesForRow(nodesToCheck, currentRow, allRoutes);
        }
        return allRoutes;
    }

    public Route findBestRoute(List<Route> routes) {
        return routes.stream()
                .max(Comparator.comparingInt(Route::getSum))
                .orElse(null);
    }

    public Route findRouteByLastNode(Node lastNode, List<Route> allRoutes) {
        return allRoutes.stream()
                .filter(route -> route.getLastNode() == lastNode)
                .findFirst()
                .orElse(null);
    }

    public List<Node> findNodesInRoutesByRow(List<Route> allRoutes, int currentRowIndex) {
        return allRoutes.stream()
                .map(Route::getLastNode)
                .filter(node -> node.getCoordinates().getRowNumber() == currentRowIndex)
                .collect(toList());
    }

    private void updateRoutesForRow(List<Node> nodesToCheck, List<Node> currentRow, List<Route> allRoutes) {
        for (Node head : nodesToCheck) {

            List<Node> candidates = findNextCandidates(head, currentRow);
            Route actualRoute = findRouteByLastNode(head, allRoutes);

            if (candidates.isEmpty()) {
                allRoutes.remove(actualRoute);
            } else if (candidates.size() > 1) {
                duplicateRoute(actualRoute, allRoutes);
            }

            updateRoutesWithNewNode(head, new ArrayList<>(candidates), allRoutes);
        }
    }

    private void updateRoutesWithNewNode(Node head, List<Node> newNodes, List<Route> allRoutes) {
        allRoutes.forEach(route -> {
            List<Node> usedNodes = new ArrayList<>();
            newNodes.forEach(node -> {
                if (route.getLastNode() == head) {
                    route.getPath().add(node);
                    usedNodes.add(node);
                }
            });
            newNodes.removeAll(usedNodes);
        });
    }

    private void duplicateRoute(Route routeToDuplicate, List<Route> allRoutes) {
        Route route = new Route();
        route.setPath(new ArrayList<>(routeToDuplicate.getPath()));
        allRoutes.add(route);
    }

    private List<Node> findNextCandidates(Node start, List<Node> choices) {
        return choices.stream()
                .filter(node -> start.isEven() != node.isEven())
                .filter(node -> start.getCoordinates().getColumnNumber() + MAX_CHILDS > node.getCoordinates().getColumnNumber())
                .filter(node -> start.getCoordinates().getColumnNumber() <= node.getCoordinates().getColumnNumber())
                .collect(toList());
    }

    private List<Route> initRouteList(Tree tree) {
        List<Node> path = new ArrayList<>();
        path.add(tree.getHead());

        Route headRoute = new Route();
        headRoute.setPath(path);

        List<Route> routeList = new ArrayList<>();
        routeList.add(headRoute);

        return routeList;
    }
}
