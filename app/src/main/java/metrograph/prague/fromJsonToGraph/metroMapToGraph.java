package metrograph.prague.fromJsonToGraph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import metrograph.prague.mapUtil.station;
import metrograph.prague.mapUtil.connection;
import metrograph.prague.mapUtil.metroMap;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class metroMapToGraph {
    public static Map<String,String> line = new HashMap<>();

     public static Map<String, List<connection>> execute(metroMap map){

        Gson gson = new Gson();

        String jsonString = gson.toJson(map);

        // créer une map copy en utilisant la structure de metroMap
        Type type = new TypeToken<metroMap>(){}.getType();
        metroMap copy = gson.fromJson(jsonString, type);

        // pour chacune des lignes, on créer un doubly linked list
        // utilisation d'une copie pour ne pas altérer les fonctionnalités de base de metroMap

        copy.forEach((k,v)->{

            Iterator<station> it = v.line().iterator();
            station previous = it.next();
            station current;

            while(it.hasNext()){
                current = it.next();

                //bout de ligne qd c null
                if (current.getTime() == 0){
                    previous.addTransfer(k,current.getName(), previous.getTime());
                }else {
                    previous.addTransfer(k,current.getName(), current.getTime());
                }

                //bout de ligne qd c null
                if (previous.getTime() == 0){
                    current.addTransfer(k, previous.getName(), current.getTime());
                }else {
                    current.addTransfer(k, previous.getName(), previous.getTime());
                }

                previous = current;
            }
        });

        Map<String, List<connection>> graph = new HashMap<>();

        //pour chacune des lignes, on met dans une hashmap les connections de chacune des stations
        copy.forEach((k,v)-> v.line().forEach(station-> {

            if(!graph.containsKey(station.getName())) {
                //si la station n'existe pas, on l'ajoute
                graph.put(station.getName(), station.getTransfer());
            }else{
                //on prend l'élément qui existe déjà, et on ajoute les connections de la station
                graph.get(station.getName()).addAll(station.getTransfer());
            }

            line.put(station.getName(), k);
            //on retirer les connections qui pointent vers la station elle-même
            graph.get(station.getName()).removeIf(connection -> connection.getStation().equals(station.getName()));

        }));

        return graph;
    }

}
