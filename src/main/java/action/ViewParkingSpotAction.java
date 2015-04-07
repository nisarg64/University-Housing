package action;

import db.table.ParkingPermitTable;
import db.view.ParkingView;
import pojo.ParkingSpot;

import java.util.List;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewParkingSpotAction extends UHAction {

    private ParkingSpot parkingSpot;
    private List<ParkingSpot> allSpots;
    private String spotId;

    public String execute(){
        String username = (String) sessionMap.get("username");
        ParkingPermitTable ppTable = new ParkingPermitTable();
        allSpots = ppTable.getResidentParkingSpots(conn,username);
        return SUCCESS;
    }

    public String getSpotInfo(){
        String username = (String) sessionMap.get("username");
        ParkingView psView = new ParkingView();
        parkingSpot = psView.getParkinSpot(conn, username, spotId);
        return SUCCESS;
    }


    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public List<ParkingSpot> getAllSpots() {
        return allSpots;
    }


    public void setAllSpots(List<ParkingSpot> allSpots) {
        this.allSpots = allSpots;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
