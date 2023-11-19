package compsec.clientserverapplication;

public class Client {
    private String id;
    private String password;
    private long counter;

    public long getCounter() {
        return counter;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

