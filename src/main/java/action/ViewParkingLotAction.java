package action;

import db.view.ParkingView;
import pojo.ParkingLot;

import java.util.List;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewParkingLotAction extends UHAction {


    private List<ParkingLot> allLots;

    public String execute(){
        String username = (String) sessionMap.get("username");
        ParkingView psView = new ParkingView();
        allLots = psView.getParkingLots(conn, username);
        return SUCCESS;
    }


    public List<ParkingLot> getAllLots() {
        return allLots;
    }

    public void setAllLots(List<ParkingLot> allLots) {
        this.allLots = allLots;
    }
}
