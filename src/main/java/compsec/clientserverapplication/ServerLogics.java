package compsec.clientserverapplication;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerLogics {
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
    @PostMapping("/signin")
    public int signIn(@RequestParam(value = "id") String id,@RequestParam(value = "password") String password) {

        //logic

        return HttpServletResponse.SC_OK;
    }
    @PostMapping("/counter")
    public int counter(@RequestParam(value = "name", defaultValue = "World") String name) {

        //logic

        return HttpServletResponse.SC_OK;
    }
}
