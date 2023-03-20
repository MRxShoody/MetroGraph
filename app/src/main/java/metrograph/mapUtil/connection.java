package metrograph.mapUtil;

public class connection {
    private final String line;
    private final String station;

    public connection(String line, String station) {
        this.line = line;
        this.station = station;
    }

    public String printConnection(){
        return station + " (" + line + " line) ";
    }
}
