package compsec.clientserverapplication;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonLogics {

    private ArrayList<String> stepsList;
    public HashMap<String,String> readFile(String json) throws ParseException {
        stepsList = new ArrayList<>();
        HashMap<String, String> stringHashMap = new HashMap<>();
        String password = "";
        String id = "";
        String ip = "";
        String port = "";
        String delay = "";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);

        JSONObject jsonObject = (JSONObject) obj;
        id = (String) jsonObject.get("id");
        stringHashMap.put("id", id);
        password = (String) jsonObject.get("password");
        stringHashMap.put("password", password);

        Object serverObject = jsonObject.get("server");
        JSONObject server = (JSONObject) serverObject;
        // Retrieve "ip" and "port" values
        ip = (String) server.get("ip");
        port = (String) server.get("port");
        stringHashMap.put("ip", ip.replace(" ",""));
        stringHashMap.put("port", port);
        Object actionsObject = jsonObject.get("actions");
        JSONObject actions = (JSONObject) actionsObject;
        // Retrieve "ip" and "port" values
        delay = (String) actions.get("delay"); 
        stringHashMap.put("delay", delay);
        // Retrieve "steps" array
        JSONArray stepsArray = (JSONArray) actions.get("steps");
        // Process each step in the array
        int counter = 1;
        for (Object step : stepsArray) {
            String actionStep = (String) step;
            try {
                // validate if it's an integer
                int amount = Integer.parseInt(actionStep.substring(9));
                if (amount <= 0) {
                    throw new IllegalArgumentException("Choose a positive value for counter!");
                }
                stringHashMap.put("action" + counter, actionStep);
                stepsList.add(actionStep);
                counter++;
            }
            catch(NumberFormatException e) {
                System.out.println("You need to input an integer!");
                return null;
            }
        }

        return stringHashMap;
    }

    public Client hashToClient(HashMap<String,String> hashMap){
        return new Client(hashMap.get("id"), hashMap.get("password"), 0L, hashMap.get("ip"), hashMap.get("port"),stepsList, hashMap.get("delay"));
    }

}
