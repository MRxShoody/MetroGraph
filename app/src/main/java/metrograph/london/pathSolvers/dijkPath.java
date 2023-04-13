package metrograph.london.pathSolvers;

import metrograph.london.graphUtils.node;

import java.util.*;

public class dijkPath extends pathSolver {

    public dijkPath(Map<String, node> graph, String line1, String start, String line2, String end) {
        super(graph, line1, start, line2, end);
    }


    @Override
    public void execute() {

        Map<String,Integer> time = new HashMap<>();

        PriorityQueue<node> queue = new PriorityQueue<>();

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


        while (path.get(endingPoint) != null) {
            route.add(endingPoint);
            endingPoint = path.get(endingPoint);
        }
        route.add(endingPoint);

        Collections.reverse(route);

        if(line1.equals(line2)) {
            route.forEach(System.out::println);
            System.out.println("Total travel time: " + graph.get(endingPoint).time + " minutes");
            return;
        }

        printPath();
        System.out.println("Total travel time: " + graph.get(endingPoint).time + " minutes");
    }

}
