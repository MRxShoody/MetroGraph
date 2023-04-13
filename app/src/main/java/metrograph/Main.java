package metrograph;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<String> testList = List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");

        Iterator<String> it = testList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        testList.forEach(System.out::println);

        for (String s : testList) {
            System.out.println(s);
        }

        for(int i = 0; i < testList.size(); i++){
            System.out.println(testList.get(i));
        }


        //System.out.println(Path.of(System.getProperty("user.dir")+"/ressource/prague.json"));
        //System.out.println(Files.exists(Path.of(System.getProperty("user.dir")+"/ressource/prague.json")));

        metrograph.prague.inputHandler.input.execute("./ressource/prague.json");

        metrograph.london.inputHandler.input.execute("./ressource/london.json");
        
    }

}
