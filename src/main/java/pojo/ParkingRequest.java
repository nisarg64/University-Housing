package pojo;

/**
 * Created by nisarg on 4/2/15.
 */
public class ParkingRequest {

    private String requestID;
    private String residentID;
    private String vehicle;
    private String handicapped;
    private String nearSpot;
    private String requestStatus;

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getHandicapped() {
        return handicapped;
    }

    public void setHandicapped(String handicapped) {
        this.handicapped = handicapped;
    }

    public String getNearSpot() {
        return nearSpot;
    }

    public void setNearSpot(String nearSpot) {
        this.nearSpot = nearSpot;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getResidentID() {
        return residentID;
    }

    public void setResidentID(String residentID) {
        this.residentID = residentID;
    }

    @Override
    public String toString() {
        return "ParkingRequest{" +
                "requestID='" + requestID + '\'' +
                ", residentID='" + residentID + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", handicapped='" + handicapped + '\'' +
                ", nearSpot='" + nearSpot + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
