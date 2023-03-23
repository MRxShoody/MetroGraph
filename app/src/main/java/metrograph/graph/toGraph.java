package metrograph.graph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import metrograph.mapUtil.connection;
import metrograph.mapUtil.metroMap;
import metrograph.mapUtil.station;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class toGraph {
     static Map<String, List<connection>> execute(metroMap map){

        Gson gson = new Gson();
        String jsonString = gson.toJson(map);

        Type type = new TypeToken<metroMap>(){}.getType();
        metroMap copy = gson.fromJson(jsonString, type);

        copy.forEach((k,v)->{
            Iterator<station> it = v.line().iterator();
            station previous = it.next();
            station current;
            while(it.hasNext()){
                current = it.next();
                previous.addTransfer(k,current.getName());
                current.addTransfer(k,previous.getName());
                previous = current;
            }
        });

        Map<String, List<connection>> graph = new HashMap<>();

        copy.forEach((k,v)-> v.line().forEach(station-> {

            if(!graph.containsKey(station.getName())) {
                graph.put(station.getName(), station.getTransfer());
            }else{
                graph.get(station.getName()).addAll(station.getTransfer());
            }

            graph.get(station.getName()).removeIf(connection -> connection.station().equals(station.getName()));

        }));

        return graph;
    }

}
