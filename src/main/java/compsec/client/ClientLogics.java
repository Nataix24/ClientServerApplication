package compsec.client;

import compsec.JsonRelated.JsonLogics;
import compsec.clientserverapplication.ServerLogics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Scanner;

public class ClientLogics {

    public static void main(String[] args) {
//        SpringApplication.run(ServerLogics.class, args);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the path to the JSON client file:");
        String filePath = myObj.nextLine();
        start(filePath);
    }
//"src/main/java/compsec/clients/test.json"
    // this is bad. but can be improved before deadline:https://www.baeldung.com/spring-boot-https-self-signed-certificate
    public static void start(String filePath){
        JsonLogics readJson = new JsonLogics();
        HashMap clientData = readJson.readFile(filePath);

        System.out.println(clientData.toString());
        System.out.println("Connecting to server...");

        //check needs to be done for SERVER and PORT.
        if(!clientData.get("ip").toString().replace(" ","").equals("localhost")){
            System.out.println("Incorrect Server Connection");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.exit(1);
        }
        if(!clientData.get("port").toString().replace(" ","").equals("8080")){
            System.out.println("Incorrect PORT");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.exit(1);
        }


        // login then if account doesnt exist then sign up then login.

        //clientData.get("ip").toString().replace(" ","")+":"+clientData.get("port").toString().replace(" ","")
        System.out.println("Signing Up..");
        WebClient webClient = WebClient.create();
        String responseBody = webClient.post()
                .uri("http://localhost:8080/signup")
                .body(BodyInserters.fromFormData("id", clientData.get("id").toString().replace(" ",""))
                        .with("password", clientData.get("password").toString().replace(" ","")))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Print the response
        assert responseBody != null;
        if(responseBody.equals("200")){
            System.out.println("Success!");
        }
        else {
            System.out.println("Response: " + responseBody);//idk what to do? do we stop? depends on the response. this is for signing in.
        }


        System.out.println("Connecting to Signing Up...");



    }

}
