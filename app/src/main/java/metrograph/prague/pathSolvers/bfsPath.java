package metrograph.prague.pathSolvers;

import metrograph.prague.fromJsonToGraph.metroMapToGraph;
import metrograph.prague.mapUtil.connection;
import metrograph.prague.mapUtil.metroMap;

import java.util.*;

public class bfsPath {

    public static void execute(metroMap map,String line1, String start,String line2, String end){

        Map<String, List<connection>> graph = metroMapToGraph.execute(map);

        Map<String, Boolean> visited = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        Map<String, String> path = new HashMap<>();

        visited.put(start, true);
        queue.add(start);
        path.put(start, null);

        while (!queue.isEmpty()) {

            String current_node = queue.poll();
            List<connection> adjList = graph.get(current_node);

            for (connection node : adjList) {
                if (!visited.containsKey(node.getStation())) {

                    visited.put(node.getStation(), true);
                    queue.add(node.getStation());

                    path.put(node.getStation(), current_node);

                    if (node.getStation().equals(end)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }


        String node = end;
        List<String> route = new ArrayList<>();

        while (path.get(node) != null) {
            route.add(node);
            node = path.get(node);
        }
        route.add(node);

        Collections.reverse(route);

        if(line1.equals(line2)) {
            route.forEach(System.out::println);
            return;
        }

        Iterator<String> it = route.iterator();
        String starte = it.next();
        String currentLine = metroMapToGraph.line.get(starte);

        System.out.println(starte);


        while (it.hasNext()) {
            String current = it.next();
            if (!metroMapToGraph.line.get(current).equals(currentLine)) {
                System.out.println(current);
                System.out.println("Transition to line " + metroMapToGraph.line.get(current));
                currentLine = metroMapToGraph.line.get(current);
            }
            System.out.println(current);
        }
    }
}
