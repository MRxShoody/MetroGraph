package metrograph.pathSolvers;

import metrograph.fromJsonToGraph.metroMapToGraph;
import metrograph.mapUtil.connection;
import metrograph.mapUtil.metroMap;

import java.util.*;

public class dijkPath {

    static class node implements Comparable<node> {
        String station;
        int distance;

        public node(String station, int distance) {
            this.station = station;
            this.distance = distance;;
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
        //Starting vertex distance should be 0

        node startNode = new node(start, 0);
        startNode.distance = 0;

        //add source vertex to the queue
        vertexQueue.add(startNode);


        //run until queue is not empty
        while (!vertexQueue.isEmpty()) {
            //Get vertex from the top of queue
            //i.e. vertex with least distance
            node actualVertex = vertexQueue.peek();

            //get adjacent vertices through connected edges
            for (connection edge : graph.get(actualVertex.station)) {


                node v = new node(edge.getStation(), Integer.MAX_VALUE);


                //If not visited then check whether the distance value could be lower

                if (!visited.containsKey(v.station)) {

                    if (actualVertex.distance + edge.getTravelTime() < v.distance) {

                        v.distance = actualVertex.distance + edge.getTravelTime();

                        path.put(v.station, actualVertex.station);

                        vertexQueue.add(v);

                        //nodes.remove(edge.getStation());
                        nodes.put(edge.getStation(), v);
                    }
                }


            }
            //remove vertex from queue
            vertexQueue.poll();
            //mark vertex as visited
            visited.put(actualVertex.station, true);
        }


        String nodeEnd = end;
        List<String> route = new ArrayList<>();
        //Loop until node is null to reach start node
        //becasue start.prev == null
        while (path.get(nodeEnd) != null) {
            route.add(nodeEnd);
            nodeEnd = path.get(nodeEnd);
        }
        route.add(nodeEnd);
        //Reverse the route - bring start to the front
        //Collections.reverse(route);
        //Output the route


/*        System.out.println(route);
        nodes.forEach((k, v) -> System.out.println(k + " " + v.distance));
        System.out.println(nodes.get(end).distance);*/


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
