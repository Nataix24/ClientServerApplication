package compsec.clientserverapplication;


import java.util.ArrayList;

public class Client {
    private String id;
    private String password;
    private String counter;

    private String server;

    private String port;

    private ArrayList<String> actionList;

    public Client(String id, String password, String counter, String server, String port, ArrayList<String> actionList) {
        this.id = id;
        this.password = password;
        this.counter = counter;
        this.server = server;
        this.port = port;
        this.actionList = actionList;
    }

    public String getCounter() {
        return counter;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public String getServer() {
        return server;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

