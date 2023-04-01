package metrograph.mapUtil;

import java.util.Collections;
import java.util.List;

public class station implements Comparable<station> {
    private Integer place;

    private final String name;
    private List<connection> transfer = Collections.emptyList();
    private int time = 0;

    public station(Integer place,String name) {
        this.place = place;
        this.name = name;
    }

    public station(Integer place,String name, List<connection> transfer) {
        this(place,name);
        this.transfer = transfer;
    }

    public station(Integer place,String name, List<connection> transfer, int time) {
        this(place,name,transfer);
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place){
        this.place = place;
    }

    public List<connection> getTransferList() {
        return transfer;
    }

    public String getTransferListString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (connection c : transfer){
            stringBuilder.append(c.printConnection());
        }
        return stringBuilder.toString();
    }

    public void addTransfer(String line, String station){
        transfer.add(new connection(line,station));
    }

    public void addTransfer(String line, String station, int time){
        transfer.add(new connection(line,station,time));
    }

    public boolean hasTransfer(){
        return !transfer.isEmpty();
    }

    @Override
    public int compareTo(station o) {
        return this.place - o.place;
    }

    public void addTransfer(List<connection> transferList) {
        transfer.addAll(transferList);
    }

    public List<connection> getTransfer() {
        return transfer;
    }

    public int getTime() {
        return time;
    }
}
