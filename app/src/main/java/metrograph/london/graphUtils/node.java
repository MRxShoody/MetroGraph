package metrograph.london.graphUtils;

import java.util.List;

 public class node implements Comparable<node> {
     public String name;
     public List<String> prev;

     public List<String> next;
     public List<transfer> transfer;

     public Integer time;
     public String nameId;
     public List<String> prevId;
     public List<String> nextId;
    private node(Integer time,String name, List<String> prev, List<String> next, List<transfer> transfer) {
        this.name = name;
        this.prev = prev;
        this.next = next;
        this.transfer = transfer;
        this.time = time;
    }

    @Override
    public int compareTo(node o) {
        return Integer.compare(this.time, o.time);
    }
}