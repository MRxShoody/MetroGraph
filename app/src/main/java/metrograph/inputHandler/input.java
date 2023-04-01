package metrograph.inputHandler;

import metrograph.fromJsonToGraph.jsonToMetroMap;
import metrograph.pathSolvers.bfsPath;
import metrograph.mapUtil.metroMap;
import metrograph.pathSolvers.dijkPath;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class input {

    private static metroMap metroMap;
    public static void execute(String fileName) {

        metroMap = jsonToMetroMap.execute(fileName);

        if(metroMap == null){
            return;
        }

        boolean running = true;

        Scanner s = new Scanner(System.in);

        Pattern pattern = Pattern.compile("(^/\\w*\\b)|((?<=\\s)\"[\\w\\s-.]+\\b)");

        //(^/\w*\b)|(\B"[\w\s]+"\B)|(\b"?[\w\s]+"?$)
        //
        while (running) {

            Matcher matcher = pattern.matcher(s.nextLine());

            String[] input = matcher.results()
                    .map(MatchResult::group)
                    .map(k -> k.replaceAll("\"","").trim())
                    .toArray(String[]::new);

            //Arrays.stream(input).forEach(System.out::println);

            switch (input[0]) {
                case "/append" -> append(input[1],input[2]);
                case "/remove" -> remove(input[1],input[2]);
                case "/add" -> addHead(input[1],input[2]);
                case "/output" -> metroMap.printLineWithTransfer(input[1]);
                case "/route" -> bfsPath.execute(metroMap,input[1], input[2],input[3],input[4]);
                case "/connect" -> metroMap.connect(input[1],input[2],input[3],input[4]);
                case "/fastest" -> dijkPath.execute(metroMap,input[1],input[2],input[3],input[4]);
                case "/exit" -> running = false;
                default -> System.out.println("Invalid command");
            }
        }

    }

    private static void append(String line, String station){
        metroMap.append(line,station);
    }

    private static void remove(String line, String station){
        metroMap.removeStation(line,station);
    }

    private static void addHead(String line, String station){
        metroMap.addHead(line,station);
    }





}


/*
input = Arrays.stream(input)
.map(k -> k.replaceAll("\"",""))
.toArray(String[]::new);
*/
