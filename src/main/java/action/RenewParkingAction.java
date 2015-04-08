package action;

import db.table.ParkingRequestTable;
import db.table.ParkingSpotTable;
import pojo.ParkingRequest;
import pojo.ParkingSpot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisarg on 4/3/15.
 */
public class RenewParkingAction extends UHAction {

    private String message = "";

    ParkingSpot parkingSpot;
    List<String> parkingSpots;
    String spotId;

    public String execute() {
        String resident_id = (String) sessionMap.get("username");
        ParkingSpotTable PSTable = new ParkingSpotTable();
        parkingSpots = PSTable.getParkingSpots(conn,resident_id );
        message = "";
        return SUCCESS;
    }

    public String submit() {
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        System.out.println(resident_id);
        System.out.println(spotId);
        ParkingSpotTable parkingSpotTable = new ParkingSpotTable();
        String renewStatus = parkingSpotTable.renewSpotRequest(conn, resident_id, spotId);

        //Reset parking request form
        //parkingSpot = new ParkingSpot();
        if(renewStatus.equals("SUCCESS"))
            message = "Request Submitted Successfully..";
        else
            message = "Invalid Parking Spot ID";
        parkingSpots = parkingSpotTable.getParkingSpots(conn,resident_id );
        return SUCCESS;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public List<String> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<String> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }
}
