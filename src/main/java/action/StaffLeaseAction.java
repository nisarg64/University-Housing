package action;

import db.table.LeaseTable;
import db.view.LeaseView;
import pojo.Lease;
import pojo.LeaseTerminationRequest;
import util.DBAccessor;

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

    public String getAllRequests() {
        LeaseView view = new LeaseView();
        allLeases = view.viewOpenLeaseRequests(conn);
        return "success";
    }

    public String getAllTerminationRequests() {
        LeaseView view = new LeaseView();
        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String approveLeaseRequest() throws SQLException {
        String query = "update " + LeaseTable.TABLE_NAME + " set " + LeaseTable.STATUS + " = '" + LeaseTable.LeaseStatus.InProgress.name() + "'" +
                " where " + LeaseTable.LEASE_NUMBER + " = " + leaseNumber;
        System.out.println(query);
        DBAccessor.executeQuery(conn, query);
        lease = new Lease();
        lease.setLeaseNumber(leaseNumber);

        // TODO set based on preferences
        lease.setApartmentNumber(null);
        lease.setPlaceNumber(null);
        return "success";
    }

    public String placeLeaseRequestOnWaitingList() throws SQLException {
        String query = "update " + LeaseTable.TABLE_NAME + " set " + LeaseTable.STATUS + " = '" + LeaseTable.LeaseStatus.WaitList.name() + "'" +
                " where " + LeaseTable.LEASE_NUMBER + " = " + leaseNumber;
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


}
