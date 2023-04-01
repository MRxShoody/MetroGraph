package metrograph.mapUtil;

public class connection {
    private final String line;
    private final String station;

    private int travelTime = 0;

    public connection(String line, String station) {
        this.line = line;
        this.station = station;
    }

    public connection(String line, String station, int time) {
        this(line,station);
        this.travelTime = time;
    }

    public String printConnection(){
        return station + " (" + line + " line) ";
    }

    public String getLine() {
        return line;
    }

    public String getStation() {
        return station;
    }

    public void getTime() {
        System.out.println(station + " " + travelTime);
    }

    public int getTravelTime() {
        return travelTime;
    }
}

