package action;

import db.table.ParkingRequestTable;
import pojo.ParkingRequest;

import java.sql.SQLException;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/4/15.
 */
public class ParkingApprovalAction extends UHAction {

    private List<ParkingRequest> parkingRequests;
    private String requestId;
    private String message;

    public String fetch(){

        ParkingRequestTable parkingRequestTable = new ParkingRequestTable();
        parkingRequests = parkingRequestTable.selectAll(conn);
        return SUCCESS;
    }

    public String approve(){

        System.out.println(requestId);
        ParkingRequestTable prTable = new ParkingRequestTable();
        String isApproved = null;
        try {
            isApproved = prTable.checkParkingAvailability(conn, requestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isApproved.equals("REJECT"))
            message = "Request Rejected";
        else
            message = "Request Approved ";
        ParkingRequestTable parkingRequestTable = new ParkingRequestTable();
        parkingRequests = parkingRequestTable.selectAll(conn);
        return SUCCESS;
    }

    public List<ParkingRequest> getParkingRequests() {
        return parkingRequests;
    }

    public void setParkingRequests(List<ParkingRequest> parkingRequests) {
        this.parkingRequests = parkingRequests;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
