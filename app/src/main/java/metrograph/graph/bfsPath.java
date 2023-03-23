package metrograph.graph;

import metrograph.mapUtil.connection;
import metrograph.mapUtil.metroMap;

import java.util.*;

public class bfsPath {

    public static void execute(metroMap map, String start, String end){
        Map<String, List<connection>> graph = toGraph.execute(map);

        Map<String, Boolean> visited = new HashMap<>();

        Queue<String> queue = new LinkedList<>();

        Map<String, String> path = new HashMap<>();

        //Visit and add start node to the queue
        visited.put(start, true);
        queue.add(start);
        path.put(start, null);

        //BFS until queue is empty and not reached to the end node
        while (!queue.isEmpty()) {
            //pop a node from queue for search operation
            String current_node = queue.poll();
            List<connection> adjList = graph.get(current_node);
            //Loop through neighbors node to find the 'end'
            for (connection node : adjList) {
                if (!visited.containsKey(node.station())) {
                    //Visit and add the node to the queue
                    visited.put(node.station(), true);
                    queue.add(node.station());
                    //update its precedings nodes
                    path.put(node.station(), current_node);
                    //If reached the end node then stop BFS
                    if (node.station().equals(end)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        //path.forEach((k,v)-> System.out.println(k + " " + v));

        String node = end;
        List<String> route = new ArrayList<>();
        //Loop until node is null to reach start node
        //becasue start.prev == null
        while (path.get(node) != null) {
            route.add(node);
            node = path.get(node);
        }
        route.add(node);
        //Reverse the route - bring start to the front
        Collections.reverse(route);
        //Output the route
        route.forEach(System.out::println);
    }



}
