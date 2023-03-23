package metrograph.mapUtil;

import java.util.HashMap;

public class metroMap extends HashMap<String, metroLine>{


    public static metroMap create(){
        return new metroMap();
    }
    public void addLine(String name, metroLine line){
        this.put(name,line);
    }
    public void removeStation(String line, String station){
        this.get(line).remove(station);
    }

    public void append(String line, String station){
        this.get(line).append(station);
    }

    public void addHead(String line, String station){
        this.get(line).addHead(station);
    }

    public void connect(String line1, String station1, String line2, String station2){
        if(line1.equals(line2) && station1.equals(station2)) return;

        if(!get(line1).exist(station1) || !get(line2).exist(station2)){
            System.out.println("Error! Such a station doesn't exist!");
            return;
        }

        this.get(line2).connect(station2,line1,station1);
        this.get(line1).connect(station1,line2,station2);
    }

    public void printMap(){
        this.forEach((k,v) -> {
            System.out.println(k);
            v.printLine();
        });
    }
    public void printLineWithTransfer(String line){
        this.get(line).printWithTransfers();
    }
    public void printLine(String line){
        this.get(line).printLine();
    }
    public void printLineIndexes(String line){
        this.get(line).printIndexes();
    }


}
