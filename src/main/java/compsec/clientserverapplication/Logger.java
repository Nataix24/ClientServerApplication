package compsec.clientserverapplication;

import java.io.*;
import java.time.LocalTime;
public class Logger {

    public static void update(String id, String counter){

        if (Integer.parseInt(counter) < 0) {
            throw new IllegalArgumentException("Choose a positive value for counter!");
        }
        
        File log = new File(""+id+"Log.txt");
        try{
            if(!log.exists()){
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append("\n\nCounter:"+counter+"\nTime:"+LocalTime.now()+"\nID:"+id);
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }

     public static Long mostRecentCounter(String id){
        Long mostRecentNumber = 0L;
        try (BufferedReader br = new BufferedReader(new FileReader(""+id+"Log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.indexOf(":")==7){
                    mostRecentNumber = Long.parseLong(line.substring(line.indexOf(":")+1));
                }
            }
        } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
        return mostRecentNumber;
     }

//    public static void main(String[] args) {
//        Logger l = new Logger();
//        l.update("4","99");
//        System.out.println(l.mostRecentCounter("4"));
//    }
}
