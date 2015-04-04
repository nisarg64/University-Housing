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


    public String execute() {
        message = "";
        return SUCCESS;
    }

    public String submit() {
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        ParkingSpotTable parkingSpotTable = new ParkingSpotTable();
        String renewStatus = parkingSpotTable.renewSpotRequest(conn, resident_id, parkingSpot);

        //Reset parking request form
        parkingSpot = new ParkingSpot();
        if(renewStatus.equals("SUCCESS"))
            message = "Request Submitted Successfully..";
        else
            message = "Invalid Parking Spot ID";
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


}
