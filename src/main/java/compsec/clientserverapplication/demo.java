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
public class demo {
    public static void main(String[] args) {
        SpringApplication.run(demo.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @PostMapping("/signup")
    public int signUp(@RequestParam(value = "id") String id,@RequestParam(value = "password") String password) {

        //logic

        return HttpServletResponse.SC_ACCEPTED;
    }
    @PostMapping("/signin")
    public int signIn(@RequestParam(value = "id") String id,@RequestParam(value = "password") String password) {

        //logic

        return HttpServletResponse.SC_ACCEPTED;
    }
    @PostMapping("/counter")
    public int counter(@RequestParam(value = "name", defaultValue = "World") String name) {

        //logic

        return HttpServletResponse.SC_ACCEPTED;
    }
}
