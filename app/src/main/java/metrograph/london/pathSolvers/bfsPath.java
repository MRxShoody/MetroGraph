package metrograph.london.pathSolvers;

import metrograph.london.graphUtils.node;
import metrograph.london.graphUtils.transfer;

import java.util.*;

public class bfsPath extends pathSolver {

    public bfsPath(Map<String, node> graph, String line1, String start, String line2, String end) {
        super(graph, line1, start, line2, end);
    }
    @Override
    public void execute() {

        Queue<String> queue = new LinkedList<>();

        queue.add(startNode.nameId);

        while (!queue.isEmpty()) {

            String current_node = queue.poll();

            List<transfer> adjList = graph.get(current_node).transfer;

            for (transfer node : adjList) {

                if (!visited.containsKey(node.transferId)) {

                    visited.put(node.transferId, true);

                    queue.add(node.transferId);

                    path.put(node.transferId, current_node);

                    if (node.transferId.equals(endingPoint)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        path.put(startNode.nameId,null);

        while (path.get(endingPoint) != null) {
            route.add(endingPoint);
            endingPoint = path.get(endingPoint);
        }
        route.add(startNode.nameId);

        Collections.reverse(route);

        if(line1.equals(line2)) {
            route.stream().map(graph::get).forEach(node-> System.out.println(node.nameId.split("/")[0]));
            return;
        }

        routeIterator = route.iterator();
        printPath();
    }
}
