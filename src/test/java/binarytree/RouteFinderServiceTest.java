package binarytree;

import binarytree.model.Node;
import binarytree.model.Route;
import binarytree.service.RouteFinderService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RouteFinderServiceTest {

    private RouteFinderService routeFinderService;

    @Before
    public void setUp() {
        routeFinderService = new RouteFinderService();
    }

    @Test
    public void findBestRoute_routeWithZeroAndFive_returnedRouteWith5() {
        List<Route> routes = new ArrayList<>();
        routes.add(createRoute(0));
        routes.add(createRoute(5));

        Route bestRoute = routeFinderService.findBestRoute(routes);

        assertEquals(Integer.valueOf(5), bestRoute.getSum());
    }

    @Test
    public void findBestRoute_noRoutesProvided_returnedNull() {
        List<Route> routes = new ArrayList<>();

        Route bestRoute = routeFinderService.findBestRoute(routes);

        assertNull(bestRoute);
    }

    @Test
    public void findRouteByLastNode_noRoutesProvided_returnedNull() {
        List<Route> routes = new ArrayList<>();

        Route result = routeFinderService.findRouteByLastNode(new Node(1), routes);

        assertNull(result);
    }

    @Test
    public void findNodesInRoutesByRow_routeHasOneNodeInFirstRow_returnedFirstRowNode() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(5, 0, 0));

        List<Route> routes = new ArrayList<>();
        routes.add(createRoute(5, nodes));

        List<Node> result = routeFinderService.findNodesInRoutesByRow(routes, 0);

        assertEquals(5, result.get(0).getValue());
    }

    private Route createRoute(int sum) {
        return createRoute(sum, Collections.emptyList());
    }

    private Route createRoute(int sum, List<Node> path) {
        Route route = new Route();
        route.setSum(sum);
        route.setPath(path);
        return route;
    }
}