package metrograph.london.pathSolvers;

import metrograph.london.graphUtils.node;

import java.util.*;

public class dijkPath {


    public static void execute(Map<String, node> graph, String line1, String start, String line2, String end) {

        Map<String, String> path = new HashMap<>();

        Map<String, Boolean> visited = new HashMap<>();

        Map<String,Integer> time = new HashMap<>();

        PriorityQueue<node> queue = new PriorityQueue<>();

        String startingPoint = start+"/"+line1;

        assert graph != null;
        node startNode = graph.get(startingPoint);

        queue.add(startNode);

        graph.forEach((k, v) ->{
            time.put(k, Objects.requireNonNullElse(v.time, 0));
            v.time = Integer.MAX_VALUE/10;
        });

        startNode.time = 0;

        while (!queue.isEmpty()) {

            node actualNode = queue.peek();

            for (metrograph.london.graphUtils.transfer transfer : graph.get(actualNode.nameId).transfer) {

                node visitingNode = graph.get(transfer.transferId);

                if (!visited.containsKey(visitingNode.nameId)) {

                    if (actualNode.time + time.get(visitingNode.nameId) < visitingNode.time) {


                        visitingNode.time = actualNode.time + time.get(actualNode.nameId);

                        path.put(visitingNode.nameId, actualNode.nameId);

                        queue.add(visitingNode);

                    }
                }
            }

            queue.poll();

            visited.put(actualNode.nameId, true);
        }

        String nodeEnd = end+"/"+line2;

        List<String> route = new ArrayList<>();

        while (path.get(nodeEnd) != null) {
            route.add(nodeEnd);
            nodeEnd = path.get(nodeEnd);
        }
        route.add(nodeEnd);

        Collections.reverse(route);

        nodeEnd = end+"/"+line2;

        if(line1.equals(line2)) {
            route.forEach(System.out::println);
            System.out.println("Total travel time: " + graph.get(nodeEnd).time + " minutes");
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

        System.out.println("Total travel time: " + graph.get(nodeEnd).time + " minutes");
    }
}
