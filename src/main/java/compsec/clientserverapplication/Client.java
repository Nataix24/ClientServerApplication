package compsec.clientserverapplication;


import java.util.ArrayList;

public class Client {
    private String id;
    private Long counter;
    private ArrayList<String> actionList;

    private String delay;

    public Client(String id, String password, Long counter, String ip, String port, ArrayList<String> actionList, String delay) {
        this.id = id;
        this.counter = counter;
        this.actionList = actionList;
        this.delay = delay;
    }

    public Long getCounter() {
        return counter;
    }

    public String getId() {
        return id;
    }


    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ArrayList<String> getActionList() {
        return actionList;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", counter=" + counter +
                ", actionList=" + actionList +
                ", delay='" + delay + '\'' +
                '}';
    }
}

