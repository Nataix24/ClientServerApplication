package compsec.JsonRelated;

import java.util.HashMap;

public class mainTesting {
    public static void main(String[] args) {
        //testing file reading
        JsonLogics readJson = new JsonLogics();
        HashMap<String,String> stringHashMap = readJson.readFile("src/main/java/compsec/clients/test.json");
        System.out.println(stringHashMap.get("password"));
        System.out.println("test");
        System.out.println(stringHashMap.get("id"));
        System.out.println(stringHashMap.get("ip"));
        System.out.println(stringHashMap.get("port"));
        System.out.println(stringHashMap.get("delay"));
        System.out.println(stringHashMap.get("action1"));
        System.out.println(stringHashMap.get("action2"));
        System.out.println(stringHashMap.get("action3"));
    }
}
