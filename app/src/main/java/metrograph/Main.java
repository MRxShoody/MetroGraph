package metrograph;

import metrograph.inputHandler.input;
import metrograph.mapUtil.metroMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("user.dir"));

        System.out.println(Files.exists(Path.of(args[0])));

        if (args[0] == null) {
            System.out.println("Error! Such a file doesn't exist!.");
            return;
        }

        input.execute(args[0]);

    }

}

/*


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
