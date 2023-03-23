package metrograph.mapUtil;

public record connection(String line, String station) {

    public String printConnection() {
        return station + " (" + line + " line) ";
    }

}
