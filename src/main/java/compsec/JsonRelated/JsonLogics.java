package compsec.JsonRelated;

import compsec.clientserverapplication.Client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonLogics {

    public HashMap readFile(String clientJsonLocation) {
        HashMap<String,String> stringHashMap = new HashMap<>();
        String password="";
        String id="";
        String ip="";
        String port="";
        String delay="";
        JSONParser parser = new JSONParser();
        ArrayList<String> actionsAL = new ArrayList<>();

        try {
            Object obj = parser.parse(new FileReader(clientJsonLocation));

            JSONObject jsonObject = (JSONObject) obj;
            id = (String) jsonObject.get(" id ");
            stringHashMap.put("id",id);
            password = (String) jsonObject.get(" password ");
            stringHashMap.put("password",password);

            Object serverObject = jsonObject.get(" server ");
            JSONObject server = (JSONObject) serverObject;
            // Retrieve "ip" and "port" values
            ip = (String) server.get(" ip ");
            port = (String) server.get(" port ");
            stringHashMap.put("ip",ip);
            stringHashMap.put("port",port);
            Object actionsObject = jsonObject.get(" actions ");
            JSONObject actions = (JSONObject) actionsObject;
            // Retrieve "ip" and "port" values
            delay = (String) actions.get(" delay ");
            stringHashMap.put("delay",delay);
            // Retrieve "steps" array
            JSONArray stepsArray = (JSONArray) actions.get(" steps ");
            // Process each step in the array
            int counter = 1;
            for (Object step : stepsArray) {
                String actionStep = (String) step;
                actionsAL.add(actionStep);
                stringHashMap.put("action" + counter,actionStep);
                counter++;
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Client c = new Client(stringHashMap.get("id"), stringHashMap.get("password"), stringHashMap.get("counter"), stringHashMap.get("server"), stringHashMap.get("port"), actionsAL);


        return stringHashMap;
    }
}
