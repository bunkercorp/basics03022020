import infrastructure.ConfigurationManager;

public class Main {

    public static void main(String[] args) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        System.out.println(configurationManager.toString());
    }
}
