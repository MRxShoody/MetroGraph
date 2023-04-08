package metrograph.london.pathSolvers;

import metrograph.london.graphUtils.node;
import metrograph.london.graphUtils.transfer;

import java.util.*;

public class bfsPath {

    public static void execute(Map<String, node> graph, String line1, String start, String line2, String end) {

        Map<String, String> path = new HashMap<>();

        Map<String, Boolean> visited = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        String startingPoint = start + "/" + line1;

        assert graph != null;
        node startNode = graph.get(startingPoint);

        queue.add(startNode.nameId);

        while (!queue.isEmpty()) {

            String current_node = queue.poll();

            List<transfer> adjList = graph.get(current_node).transfer;

            for (transfer node : adjList) {

                if (!visited.containsKey(node.transferId)) {

                    visited.put(node.transferId, true);

                    queue.add(node.transferId);

                    path.put(node.transferId, current_node);

                    if (node.transferId.equals(end + "/" + line2)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        path.put(startNode.nameId,null);

        List<String> route = new ArrayList<>();
        String endNode = end + "/" + line2;


        while (path.get(endNode) != null) {
            route.add(endNode);
            endNode = path.get(endNode);
        }
        route.add(startNode.nameId);

        Collections.reverse(route);

        if(line1.equals(line2)) {
            route.stream().map(graph::get).forEach(node-> System.out.println(node.nameId.split("/")[0]));
            return;
        }

        Iterator<String> it = route.iterator();
        String starte = it.next();
        System.out.println(graph.get(starte).nameId.split("/")[0]);
        String currentLine = graph.get(starte).nameId.split("/")[1];


        while (it.hasNext()) {
            String current = it.next();
            if (!graph.get(current).nameId.split("/")[1].equals(currentLine)) {
                System.out.println("Transition to line " +graph.get(current).nameId.split("/")[1]);
                currentLine = graph.get(current).nameId.split("/")[1];
            }
            System.out.println(graph.get(current).nameId.split("/")[0]);
        }
    }

}
