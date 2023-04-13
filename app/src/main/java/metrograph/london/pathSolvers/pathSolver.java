package metrograph.london.pathSolvers;

import metrograph.london.graphUtils.node;

import java.util.*;

public abstract class pathSolver{

    Map<String, String> path;
    Map<String, Boolean> visited;
    node startNode;
    Iterator<String> routeIterator;
    Map<String, node> graph;
    String startingPoint;
    String endingPoint;

    String line1;
    String line2;

    List<String> route;


    protected pathSolver(Map<String, node> graph, String line1, String start, String line2, String end) {
        this.graph = graph;
        this.path = new HashMap<>();
        this.visited = new HashMap<>();
        this.route = new ArrayList<>();
        this.line1 = line1;
        this.line2 = line2;

        startingPoint = start+"/"+line1;
        endingPoint = end+"/"+line2;

        assert graph != null;
        startNode = graph.get(startingPoint);
    }


    public abstract void execute();

    protected void printPath(){
        String iteratorString = routeIterator.next();
        System.out.println(graph.get(iteratorString).nameId.split("/")[0]);
        String currentLine = graph.get(iteratorString).nameId.split("/")[1];


        while (routeIterator.hasNext()) {
            String current = routeIterator.next();
            if (!graph.get(current).nameId.split("/")[1].equals(currentLine)) {
                System.out.println("Transition to line " +graph.get(current).nameId.split("/")[1]);
                currentLine = graph.get(current).nameId.split("/")[1];
            }
            System.out.println(graph.get(current).nameId.split("/")[0]);
        }
    }

}
