package metrograph.fromToJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import metrograph.mapUtil.metroLine;
import metrograph.mapUtil.metroMap;
import metrograph.mapUtil.station;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class jsonToMetroMap {

    public static metroMap execute(String fileName){
        metroMap map = metroMap.create();

        try (FileInputStream fos = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fos,
                     StandardCharsets.UTF_8)){



            Map<String, Map<String, station>> jsonMap = new Gson().fromJson(isr
                    , new TypeToken<Map<String, Map<String, station>>>() {
                    }.getType());


            jsonMap.forEach((lineName, stations) -> map.put(lineName, new metroLine(stations)));

            return map;

        }catch (IOException e){
            System.out.println("Error! Such a file doesn't exist!");
        }catch (Exception e){
            System.out.println("Incorrect file");
        }
        return null;
    }
}
