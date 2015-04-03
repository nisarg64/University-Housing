package action;

import db.view.ParkingView;
import pojo.ParkingSpot;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewParkingSpotAction extends UHAction {
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    private ParkingSpot parkingSpot;


    public String execute(){
        String username = (String) sessionMap.get("username");
        ParkingView psView = new ParkingView();
        parkingSpot = psView.getParkinSpot(conn, username);
        return SUCCESS;
    }
}
