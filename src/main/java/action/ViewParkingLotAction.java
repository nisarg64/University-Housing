package action;

import db.view.ParkingView;
import pojo.ParkingLot;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewParkingLotAction extends UHAction {


    private ParkingLot parkingLot;

    public String execute(){
        String username = (String) sessionMap.get("username");
        ParkingView psView = new ParkingView();
        parkingLot = psView.getParkinLot(conn, username);
        return SUCCESS;
    }


    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

}
