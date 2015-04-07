package action;

import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.Lease;
import pojo.LeaseTerminationRequest;
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

    private List<Lease> allLeases;
    private List<LeaseTerminationRequest> allTerminationLeases;
    private int leaseNumber;
    private Lease lease;
    private int damageFees;
    private int requestNumber;
    private LeaseTerminationRequest leaseTerminationRequest;

    public String getAllRequests() {
        LeaseView view = new LeaseView();
        allLeases = view.viewOpenLeaseRequests(conn);
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
        String query = "update " + LeaseTable.TABLE_NAME + " set " + LeaseTable.STATUS + " = '" + LeaseTable.RequestStatus.InProgress.name() + "'" +
                " where " + LeaseTable.REQUEST_NUMBER + " = " + leaseNumber;
        System.out.println(query);
        DBAccessor.executeQuery(conn, query);
        lease = new Lease();
        lease.setLeaseNumber(leaseNumber);

        // TODO set based on preferences
        lease.setHousingId(null);
        lease.setLocationNumber(null);
        return "success";
    }

    public String approveLeaseTerminationRequest() throws SQLException {
        String sql = "update " + LeaseTerminationRequestTable.TABLE_NAME +
                " set " + LeaseTerminationRequestTable.INSPECTION_DATE+ " = ? where "+
                LeaseTerminationRequestTable.REQUEST_NUMBER + " = " + requestNumber;

        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, new Date(leaseTerminationRequest.getInspectionDate().getTime()));
        stmt.executeUpdate();
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String placeLeaseRequestOnWaitingList() throws SQLException {
        String query = "update " + LeaseTable.TABLE_NAME + " set " + LeaseTable.STATUS + " = '" + LeaseTable.RequestStatus.WaitList.name() + "'" +
                " where " + LeaseTable.REQUEST_NUMBER + " = " + leaseNumber;
        DBAccessor.executeQuery(conn, query);
        lease = new Lease();
        lease.setLeaseNumber(leaseNumber);
        return "success";
    }


    public String viewLeaseToApprove() {
        LeaseView view = new LeaseView();
        lease = view.viewLease(conn, leaseNumber);
        lease.setCanApprove(true);
        return SUCCESS;
    }

    public String approveTerminationLease(){
        return "success";
    }

    public List<Lease> getAllLeases() {
        return allLeases;
    }

    public void setAllLeases(List<Lease> allLeases) {
        this.allLeases = allLeases;
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
