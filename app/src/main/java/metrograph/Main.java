package metrograph;

import metrograph.fromJsonToGraph.jsonToMetroMap;
import metrograph.inputHandler.input;
import metrograph.mapUtil.metroMap;


public class Main {

    public static void main(String[] args) {

        //System.out.println(System.getProperty("user.dir"));

        if (args[0] == null) {
            System.out.println("Error! Such a file doesn't exist!.");
            return;
        }

        metroMap metroMap = jsonToMetroMap.execute(args[0]);

        /*
        Map<String, List<connection>> metroGraph = metroMapToGraph.execute(metroMap);


        metroGraph.forEach((k,v) -> {
            System.out.println(k);
            //v.forEach(m->System.out.println(m.printConnection()));
            v.forEach(connection::getTime);
            System.out.println("\n");
        });
*/

        //dijkPath.execute(metroMap,"Vysehrad","Namesti Republiky");

        input.execute(args[0]);

    }

}

/*

System.out.println(System.getProperty("user.dir"));
System.out.println(metroMap.create(args[0]));
metro.create(args[0]).printMap();

String fileName = "./src/ressource/baltimore.json";

try (FileOutputStream fos = new FileOutputStream(fileName);
     OutputStreamWriter isr = new OutputStreamWriter(fos,
             StandardCharsets.UTF_8)) {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    HashMap<String,metroLine> map = new HashMap();

    metroLine metro1 = metroLine.create(args[0]);
    metroLine metro2 = metroLine.create(args[0]);

    map.put("test",metro1);
    map.put("test2",metro2);

    JsonElement tree = gson.toJsonTree(map);
    gson.toJson(tree, isr);
}

*/
