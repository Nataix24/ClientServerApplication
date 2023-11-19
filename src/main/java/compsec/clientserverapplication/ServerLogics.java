package compsec.clientserverapplication;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class ServerLogics {
    ArrayList<Client> clientList = new ArrayList<Client>();

    public static void main(String[] args) {
        SpringApplication.run(ServerLogics.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @PostMapping("/signup")
    public int signUp(@RequestParam(value = "id") String id,@RequestParam(value = "password") String password) {

        //logic
        System.out.println("id:"+id);
        System.out.println("password:"+password);

        return HttpServletResponse.SC_OK;
    }
    @PostMapping("/login")
    public int signIn(@RequestParam(value = "id") String id,@RequestParam(value = "password") String password) {
        //hash password here (.equalss(HASH(password)))
        for (Client c: clientList) {
            if(c.getId().equals(id)&&c.getId().equals(password)){
                return HttpServletResponse.SC_OK;
            }
        }

        return HttpServletResponse.SC_UNAUTHORIZED;
    }
    @PostMapping("/counter")
    public int counter(@RequestParam(value = "id", defaultValue = "value") String name) {

        //logic

        return HttpServletResponse.SC_OK;
    }
}
