package metrograph.london.inputHandler;


import metrograph.london.pathSolvers.bfsPath;
import metrograph.london.pathSolvers.dijkPath;
import metrograph.london.fileToGraph.fileToGraph;
import metrograph.london.graphUtils.node;
import metrograph.london.pathSolvers.pathSolver;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class input {

    public static void execute(String fileName) {

        Map<String, node> graph = fileToGraph.execute(fileName);

        if (graph == null) {
            return;
        }

        boolean running = true;

        Scanner s = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(^/\\w*\\b)|((?<=\\s)\"[&\\w\\s-.]*\")");

        //(^/\w*\b)|(\B"[\w\s]+"\B)|(\b"?[\w\s]+"?$)
        //
        while (running) {

            Matcher matcher = pattern.matcher(s.nextLine());

            String[] input = matcher.results()
                    .map(MatchResult::group)
                    .map(k -> k.replaceAll("\"", "").trim())
                    .toArray(String[]::new);

            //Arrays.stream(input).forEach(System.out::println);

            pathSolver solver;

            switch (input[0]) {
                case "/route" -> {
                    solver = new bfsPath(graph, input[1], input[2], input[3], input[4]);
                    solver.execute();
                }
                case "/fastest" -> {
                    solver = new dijkPath(graph, input[1], input[2], input[3], input[4]);
                    solver.execute();
                }
                case "/exit" -> running = false;
                default -> System.out.println("Invalid command");
            }
        }

    }
}
