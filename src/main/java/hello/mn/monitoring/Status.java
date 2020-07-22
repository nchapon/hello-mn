package hello.mn.monitoring;

public class Status {

    public final static Status UP = new Status("UP");
    public final static Status OUT_OF_SERVICE = new Status("OUT_OF_SERVICE");
    private final String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
