package pojo;

import java.sql.Connection;

/**
 * Created by nisarg on 4/2/15.
 */
public class ParkingRequest {

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

    @Override
    public String toString() {
        return "ParkingRequest{" +
                "vehicle='" + vehicle + '\'' +
                ", handicapped='" + handicapped + '\'' +
                ", nearSpot='" + nearSpot + '\'' +
                '}';
    }
}