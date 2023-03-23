package metrograph.mapUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class metroLine {

    private final LinkedList<station> map = new LinkedList<>();

    public static metroLine create(String path) {
        return new metroLine(Path.of(path));
    }
    private metroLine(Path path) {
        try {
            AtomicInteger compteur = new AtomicInteger();
            map.addAll(Files.readAllLines(path).stream().map(k -> new station(compteur.getAndIncrement(),k)).toList());
        } catch (Exception e) {
            System.out.println("Error! Such a file doesn't exist!.");
        }
    }

    public metroLine(Map<String,station> map){
        map.forEach((k,v) -> this.map.add(new station(Integer.parseInt(k),v.getName(),v.getTransferList())));
    }


    public void append(String station){
        map.add(new station(map.size()+1,station));
    }

    public void addHead(String station){
        map.forEach(s -> s.setPlace(s.getPlace()+1));
        map.addFirst(new station(1,station));
    }

    public void remove(String station){
        int i = 0;
        for (station s : map) {
            if(s.getName().equals(station)){
                map.remove(i);
                break;
            }
            i++;
        }

        for(int j = i; j < map.size(); j++){
            station s = map.get(j);
            s.setPlace(s.getPlace()-1);
        }
    }

    public void connect(String station1, String line2, String station2) {
        station s1 = map.stream().filter(s -> s.getName().equals(station1)).findFirst().orElse(null);
        assert s1 != null;
        s1.addTransfer(line2,station2);

    }

    public boolean exist(String station){
        return map.stream().anyMatch(s -> s.getName().equals(station));
    }

    public void printIndexes() {
        map.forEach(s -> System.out.println(s.getPlace() + " - " + s.getName()));
    }

    public void printLine(){
        Collections.sort(map);

        map.addFirst(new station(null,"depot"));
        map.addLast(new station(null,"depot"));
        map.addLast(new station(null,"debugger"));

        Iterator<station> iterator = map.iterator();
        String first = iterator.next().getName();
        String middle = iterator.next().getName();
        String last = iterator.next().getName();
        while(iterator.hasNext()){
            System.out.println(first + " - " + middle + " - " + last);
            first = middle;
            middle = last;
            last = iterator.next().getName();
        }

        map.removeLast();
        map.removeLast();
        map.removeFirst();
    }

    public void printWithTransfers(){
        Collections.sort(map);

        map.addFirst(new station(null,"depot"));
        map.addLast(new station(null,"depot"));
        for (station s : map){

            if(s.hasTransfer()){
                System.out.println(s.getName() + " - " + s.getTransferListString());
            }else {
                System.out.println(s.getName());
            }

        }

        map.removeFirst();
        map.removeLast();

    }

    public LinkedList<station> line(){
        return map;
    }

    public station get(String station) {
        return map.stream().filter(s -> s.getName().equals(station)).findFirst().orElse(null);
    }
}

//return map.stream().filter(s -> !s.isBlank()).reduce((s1, s2) -> s1 + " - " + s2).orElse("depot - depot");
