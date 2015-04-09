package pojo;

/**
 * User: Nikhil
 * Date: 03-04-15
 */
public class LeasePreference {

    private int requestNumber;
    private int sequenceNumber;
    private String type;
    private String hallId;
    private String hallName;

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    @Override
    public String toString() {
        return "LeasePreference{" +
                "requestNumber=" + requestNumber +
                ", sequenceNumber=" + sequenceNumber +
                ", type='" + type + '\'' +
                ", hallId='" + hallId + '\'' +
                ", hallName='" + hallName + '\'' +
                '}';
    }
}
