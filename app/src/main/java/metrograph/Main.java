package metrograph;

public class Main {

    public static void main(String[] args) {

        //System.out.println(Path.of(System.getProperty("user.dir")+"/ressource/prague.json"));
        //System.out.println(Files.exists(Path.of(System.getProperty("user.dir")+"/ressource/prague.json")));

        metrograph.prague.inputHandler.input.execute("./ressource/prague.json");

        metrograph.london.inputHandler.input.execute("./ressource/london.json");
        
    }

}
