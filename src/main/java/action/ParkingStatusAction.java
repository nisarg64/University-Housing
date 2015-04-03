package action;

import db.table.ParkingRequestTable;
import pojo.ParkingRequest;

/**
 * Created by nisarg on 4/2/15.
 */
public class ParkingStatusAction extends UHAction {

    public ParkingRequest getParkingRequest() {
        return parkingRequest;
    }

    public void setParkingRequest(ParkingRequest parkingRequest) {
        this.parkingRequest = parkingRequest;
    }

    private ParkingRequest parkingRequest;

    public String execute(){
        String username = (String) sessionMap.get("username");
        ParkingRequestTable prTable = new ParkingRequestTable();
        parkingRequest = prTable.getParkingRequest(conn,username);
        return SUCCESS;
    }
}
