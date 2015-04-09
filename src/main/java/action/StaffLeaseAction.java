package action;

import db.table.LeaseRequestTable;
import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import db.view.LeaseRequestView;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.Lease;
import pojo.LeaseRequest;
import pojo.LeaseTerminationRequest;
import pojo.ProposedHousing;
import util.DBAccessor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/5/15.
 */
public class StaffLeaseAction extends UHAction{

    private List<LeaseRequest> allLeaseRequests;
    private List<LeaseTerminationRequest> allTerminationLeases;
    private int leaseNumber;
    private Lease lease;
    private LeaseRequest leaseRequest;
    private int damageFees;
    private int requestNumber;
    private LeaseTerminationRequest leaseTerminationRequest;

    public String getAllRequests() {
        allLeaseRequests = (new LeaseRequestView()).viewOpenLeaseRequests(conn);
        return "success";
    }

    public String getAllLeaseTerminationRequests() {
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String editLeaseTerminationRequestToApprove() {
        return SUCCESS;
    }

    public String approveLeaseRequest() throws SQLException {
        LeaseRequest leaseRequest = (new LeaseRequestView().viewLeaseRequest(conn, requestNumber));
        if (!leaseRequest.isUsePrivateAccommodation()) {
            Lease lease = new Lease();
            lease.setLeaseRequest(leaseRequest);
            LeaseTable table = new LeaseTable();
            lease.setHousingId(leaseRequest.getProposedHousing().getProposedHousingId());
            lease.setLocationNumber(leaseRequest.getProposedHousing().getProposedLocationNumber());
            lease.setLeaseNumber(table.insert(conn, lease));
        }
        LeaseRequestTable table = new LeaseRequestTable();
        String username = (String) sessionMap.get("username");
        table.updateStatusByStaff(conn, requestNumber, LeaseTable.RequestStatus.InProgress, username.trim());
        return "success";
    }

    public String approveLeaseTerminationRequest() throws SQLException {
        String sql = "update " + LeaseTerminationRequestTable.TABLE_NAME +
                " set " + LeaseTerminationRequestTable.INSPECTION_DATE+ " = ? where "+
                LeaseRequestTable.REQUEST_NUMBER + " = " + requestNumber;

        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, new Date(leaseTerminationRequest.getInspectionDate().getTime()));
        stmt.executeUpdate();
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String placeLeaseRequestOnWaitingList() throws SQLException {
        // TODO
        String query = "update " + LeaseRequestTable.TABLE_NAME + " set " + LeaseRequestTable.STATUS + " = '" + LeaseTable.RequestStatus.WaitList.name() + "'" +
                " where " + LeaseTable.LEASE_NUMBER + " = " + leaseNumber;
        DBAccessor.executeQuery(conn, query);
        lease = new Lease();
        lease.setLeaseNumber(leaseNumber);
        return "success";
    }


    public String viewLeaseToApprove() {
        LeaseRequest leaseRequest = (new LeaseRequestView()).viewLeaseRequest(conn, leaseNumber);
        LeaseView view = new LeaseView();
        ProposedHousing proposedHousing = view.getProposedHousingForLease(conn, leaseRequest);
        if (proposedHousing == null) {
            leaseRequest.setCanApprove(false);
            return "waiting";
        } else {
            leaseRequest.setCanApprove(true);
            leaseRequest.setProposedHousing(proposedHousing);
            return SUCCESS;
        }
    }

    public String approveTerminationLease(){
        return "success";
    }

    public List<LeaseRequest> getAllLeaseRequests() {
        return allLeaseRequests;
    }

    public void setAllLeaseRequests(List<LeaseRequest> allLeaseRequests) {
        this.allLeaseRequests = allLeaseRequests;
    }

    public List<LeaseTerminationRequest> getAllTerminationLeases() {
        return allTerminationLeases;
    }

    public void setAllTerminationLeases(List<LeaseTerminationRequest> allTerminationLeases) {
        this.allTerminationLeases = allTerminationLeases;
    }

    public int getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(int leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public int getDamageFees() {
        return damageFees;
    }

    public void setDamageFees(int damageFees) {
        this.damageFees = damageFees;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public LeaseTerminationRequest getLeaseTerminationRequest() {
        return leaseTerminationRequest;
    }

    public void setLeaseTerminationRequest(LeaseTerminationRequest leaseTerminationRequest) {
        this.leaseTerminationRequest = leaseTerminationRequest;
    }
}
