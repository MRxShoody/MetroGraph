package metrograph.london.fileToGraph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import metrograph.london.graphUtils.node;
import metrograph.london.graphUtils.transfer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileToGraph {

    public static Map<String, node> execute(String args) {

        try (FileInputStream fos = new FileInputStream(args);
             InputStreamReader isr = new InputStreamReader(fos,
                     StandardCharsets.UTF_8)){

            Map<String, List<node>> jsonMap = new Gson().fromJson(isr
                    , new TypeToken<Map<String, List<node>>>() {
                    }.getType());

            Map<String,node> graph = new HashMap<>();

            jsonMap.forEach((k,v) -> v.forEach(node -> {

                node.nameId = node.name+"/"+k;

                node.prevId = node.prev.stream().map(s -> s+"/"+k).toList();

                node.nextId = node.next.stream().map(s -> s+"/"+k).toList();

                if(node.prev.size() != 0) {
                    node.prev.forEach(s -> node.transfer.add(new transfer(s, k)));
                }

                if(node.next.size() != 0) {
                    node.next.forEach(s -> node.transfer.add(new transfer(s, k)));
                }

                node.transfer.forEach(transfer -> transfer.transferId = transfer.station+"/"+transfer.line);

                node.transfer.forEach(transfer -> transfer.transferId = transfer.station+"/"+transfer.line);

                graph.put(node.nameId, node);
            }));

            return graph;

        }catch (IOException e){

            System.out.println("Error! Such a file doesn't exist!");

        }catch (Exception e){

            System.out.println("Incorrect file");

        }

        return null;
    }
}

