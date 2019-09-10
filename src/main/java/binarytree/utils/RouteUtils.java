package binarytree.utils;

import binarytree.model.Node;
import binarytree.model.Route;

import java.util.List;

public class RouteUtils {

    private RouteUtils() {
    }

    public static void calculateRoutesSum(List<Route> routes) {
        routes.forEach(RouteUtils::calculateRouteSum);
    }

    public static void calculateRouteSum(Route route) {
        int routeSum = route.getPath().stream()
                .mapToInt(Node::getValue)
                .sum();
        route.setSum(routeSum);
    }
}
