package compsec.clientserverapplication;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;



@SpringBootApplication
@RestController
public class ServerLogics {
    HashMap<String,String> clients = new HashMap<>();
    public static void main(String[] args) {
        SpringApplication.run(ServerLogics.class, args);
    }

    @PostMapping("/receive")
    public int signUp(@RequestBody String json) throws ParseException {

        System.out.println(clients.toString());

        JsonLogics parse = new JsonLogics();
        HashMap<String,String> clientCurrent = parse.readFile(json);
        Client c= parse.hashToClient(parse.readFile(json));
        for (String s: clients.keySet()) { //case sensitive?
            if(clientCurrent.get("id").replace(" ","").equals(s.replace(" ",""))&&clientCurrent.get("password").replace(" ","").equals(clients.get(s))){
                c.setCounter(Logger.mostRecentCounter(clientCurrent.get("id").replace(" ","")));
                execute(c);
                return HttpServletResponse.SC_OK;
            }
            else if (clientCurrent.get("id").replace(" ","").equals(s)){
                return HttpServletResponse.SC_UNAUTHORIZED;
            }
        }

        clients.put(clientCurrent.get("id"),clientCurrent.get("password"));
        execute(c);
        return HttpServletResponse.SC_OK;
    }

    public void execute(Client client) {
        Thread newThread = new Thread(new tasks(client));
        newThread.start();
    }
    public class tasks implements Runnable {
        Client client;
        public tasks(Client client){
            this.client = client;
        }

        @Override
        public void run() {
            ArrayList<String> list = client.getActionList();
            for (String s: list) {
                long amount = Long.parseLong(s.substring(8).replace(" ", ""));
                if(s.substring(0,8).equalsIgnoreCase("INCREASE")){
                    client.setCounter(client.getCounter()+
                            amount);
                    Logger.update(client.getId().replace(" ",""),client.getCounter().toString());
                    //write to log
                }
                else if(s.substring(0,8).equalsIgnoreCase("DECREASE")){
                    client.setCounter(client.getCounter()-
                            amount);
                    Logger.update(client.getId().replace(" ",""),client.getCounter().toString());
                    //write to log
                }
                else{
                    throw new UnsupportedOperationException("Invalid action received:\""+s+"\"");
                }
                try {
                    Thread.sleep((Long.parseLong(client.getDelay().replace(" ",""))*1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("counter after actions:"+client.getCounter());

            }
        }
    }
}