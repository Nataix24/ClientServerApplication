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
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class ServerLogics {
    ArrayList<Client> clients = new ArrayList<>();
    public static void main(String[] args) {
        SpringApplication.run(ServerLogics.class, args);
    }

    @PostMapping("/recieve")
    public int signUp(@RequestBody String json) throws ParseException {
        JsonLogics parse = new JsonLogics();
        parse.readFile(json);
        //clients.add();

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
            
            throw new UnsupportedOperationException("Unimplemented method 'run'");
        }
        
    }

   
}
