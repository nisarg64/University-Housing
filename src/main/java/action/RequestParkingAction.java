package action;

import db.table.ParkingRequestTable;
import pojo.ParkingRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisarg on 3/31/15.
 */
public class RequestParkingAction extends UHAction {

    private List<String> vehicleType;
    private List<String> isHandicapped;
    private List<String> nearbySpot;
    private String message = "";
    private ParkingRequest parkingRequest;

    public RequestParkingAction(){
        vehicleType = new ArrayList<String>();
        vehicleType.add("Bike");
        vehicleType.add("Compact Cars");
        vehicleType.add("Standard Cars");
        vehicleType.add("Large Cars");

        isHandicapped = new ArrayList<String>();
        isHandicapped.add("Yes");
        isHandicapped.add("No");

        nearbySpot = new ArrayList<String>();
        nearbySpot.add("Yes");
        nearbySpot.add("No");
    }

    public String execute() {
        message = "";
        return SUCCESS;
    }

    public String submit() throws SQLException {
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        ParkingRequestTable prTable = new ParkingRequestTable();
        prTable.insertRequest(conn, resident_id, parkingRequest);

        //Reset parking request form
        parkingRequest = new ParkingRequest();
        message = "Request Submitted Successfully..";
        return SUCCESS;
    }


    public String display() {
        return NONE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParkingRequest getParkingRequest() {
        return parkingRequest;
    }

    public void setParkingRequest(ParkingRequest parkingRequest) {
        this.parkingRequest = parkingRequest;
    }

    public List<String> getNearbySpot() {
        return nearbySpot;
    }

    public void setNearbySpot(List<String> nearbySpot) {
        this.nearbySpot = nearbySpot;
    }

    public List<String> getIsHandicapped() {
        return isHandicapped;
    }

    public void setIsHandicapped(List<String> isHandicapped) {
        this.isHandicapped = isHandicapped;
    }

    public List<String> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(List<String> vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "RequestParkingAction{" +
                "vehicleType=" + vehicleType +
                ", isHandicapped=" + isHandicapped +
                ", nearbySpot=" + nearbySpot +
                ", message='" + message + '\'' +
                ", parkingRequest=" + parkingRequest +
                '}';
    }
}
