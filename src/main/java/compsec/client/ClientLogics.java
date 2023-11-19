package compsec.client;

import compsec.PasswordHashing.HashingLogics;
import compsec.clientserverapplication.JsonLogics;
import compsec.clientserverapplication.ServerLogics;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



public class ClientLogics {
    public static void main(String[] args) throws FileNotFoundException, ParseException, InterruptedException, ExecutionException {
        boolean exit = false;
        while(!exit){

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the path to the JSON client file or type exit:");
            String file = input.nextLine();
            input.close();
            if(file.equals("exit")){
                exit= true;
            }else{
                File myObj = new File(file);
                Scanner myReader = new Scanner(myObj);
                String data = "";
                while (myReader.hasNextLine()) {
                   data += myReader.nextLine(); 
                }
                myReader.close();
                String[] splitData= data.split("\"");
                for (int i = 0; i < splitData.length; i++) {
                    if(splitData[i].equals("password")){
                        int index = i+2;
                        HashingLogics hash = new HashingLogics(splitData[index]);
                        splitData[index] = hash.getHashedPassword();
                    }
                }
                data = "";
                for (int i = 0; i < splitData.length; i++) {
                    data += splitData[i];
                }

                JsonLogics parser = new JsonLogics();
                HashMap<String,String> parsedData = parser.readFile(data);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(parsedData.get("ip")+":"+parsedData.get("port")))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
                CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                HttpResponse<String> response = futureResponse.get();
                System.out.println(response.statusCode());
            }
        }
//        start(filePath);
    }

}
