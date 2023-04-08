package metrograph.prague.pathSolvers;

import metrograph.prague.fromJsonToGraph.metroMapToGraph;
import metrograph.prague.mapUtil.connection;
import metrograph.prague.mapUtil.metroMap;

import java.util.*;

public class dijkPath {

    static class node implements Comparable<node> {
        String station;
        int distance;

        public node(String station, int distance) {
            this.station = station;
            this.distance = distance;
        }

        @Override
        public int compareTo(node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void execute(metroMap map,String line1, String end,String line2, String start) {

        Map<String, List<connection>> graph = metroMapToGraph.execute(map);

        Map<String, String> path = new HashMap<>();

        Map<String, Boolean> visited = new HashMap<>();

        Map<String, node> nodes = new HashMap<>();

        PriorityQueue<node> vertexQueue = new PriorityQueue<>();

        node startNode = new node(start, 0);
        startNode.distance = 0;

        vertexQueue.add(startNode);

        while (!vertexQueue.isEmpty()) {

            node actualVertex = vertexQueue.peek();

            for (connection edge : graph.get(actualVertex.station)) {

                node v = new node(edge.getStation(), Integer.MAX_VALUE);

                if (!visited.containsKey(v.station)) {

                    if (actualVertex.distance + edge.getTravelTime() < v.distance) {

                        v.distance = actualVertex.distance + edge.getTravelTime();

                        path.put(v.station, actualVertex.station);

                        vertexQueue.add(v);

                        nodes.put(edge.getStation(), v);
                    }
                }
            }

            vertexQueue.poll();

            visited.put(actualVertex.station, true);
        }


        String nodeEnd = end;
        List<String> route = new ArrayList<>();

        while (path.get(nodeEnd) != null) {
            route.add(nodeEnd);
            nodeEnd = path.get(nodeEnd);
        }
        route.add(nodeEnd);

        if(line1.equals(line2)) {
            route.forEach(System.out::println);
            System.out.println("Total travel time: " + nodes.get(end).distance + " minutes");
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
        System.out.println("Total travel time: " + nodes.get(end).distance + " minutes");
    }

}
