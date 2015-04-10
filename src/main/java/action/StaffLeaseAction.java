package action;

import db.table.LeaseRequestTable;
import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import db.table.LeaseUtils;
import db.view.LeaseRequestView;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.*;
import util.DBAccessor;
import util.RoommateFinder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private LeasePreference preference1;
    private LeasePreference preference2;
    private LeasePreference preference3;
    private ProposedHousing proposedHousing;
    private List<PotentialRoommate> potentialRoommates;


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
            lease.setHousingId(proposedHousing.getProposedHousingId());
            lease.setLocationNumber(proposedHousing.getProposedLocationNumber());
            lease.setHousingName(proposedHousing.getProposedHousingName());
            LeaseTable table = new LeaseTable();
            lease.setLeaseNumber(table.insert(conn, lease));

            this.lease = lease;
        }
        LeaseRequestTable table = new LeaseRequestTable();
        String username = (String) sessionMap.get("username");
        table.updateStatusByStaff(conn, requestNumber, LeaseTable.RequestStatus.InProgress, username.trim());
        return "success";
    }

    public String waitlistLeaseRequest() throws SQLException {
        LeaseRequestTable table = new LeaseRequestTable();
        String username = (String) sessionMap.get("username");
        table.updateStatusByStaff(conn, requestNumber, LeaseTable.RequestStatus.WaitList, username.trim());
        return "success";
    }

    public String approveLeaseTerminationRequest() throws SQLException {
        String sql = "update " + LeaseTerminationRequestTable.TABLE_NAME +
                " set " + LeaseTerminationRequestTable.INSPECTION_DATE+ " = ? where "+
                LeaseRequestTable.REQUEST_NUMBER + " = " + requestNumber;

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
        leaseRequest = (new LeaseRequestView()).viewLeaseRequest(conn, requestNumber);
        LeaseView view = new LeaseView();
        ProposedHousing proposedHousing = view.getProposedHousingForLease(conn, leaseRequest);
        potentialRoommates = view.getPotentialRoommates(conn, requestNumber);

        if (proposedHousing == null) {
            leaseRequest.setCanApprove(false);
            return "waiting";
        } else {
            leaseRequest.setCanApprove(true);

            if(leaseRequest.getPreference1() == null || leaseRequest.getPreference1().getType().equalsIgnoreCase("-1")){
                this.preference1 = new LeasePreference();
                this.preference1.setType("No Preferences Specified");
                this.preference1.setHallName("No Preferences Specified");
            }else{
                this.preference1 = leaseRequest.getPreference1();
            }
            if(leaseRequest.getPreference2() == null || leaseRequest.getPreference2().getType().equalsIgnoreCase("-1")){
                this.preference2 = new LeasePreference();
                this.preference2.setType("No Preferences Specified");
                this.preference2.setHallName("No Preferences Specified");
            }else{
                this.preference2 = leaseRequest.getPreference2();
            }
            if(leaseRequest.getPreference3() == null || leaseRequest.getPreference3().getType().equalsIgnoreCase("-1")){
                this.preference3 = new LeasePreference();
                this.preference3.setType("No Preferences Specified");
                this.preference3.setHallName("No Preferences Specified");
            }else{
                this.preference3 = leaseRequest.getPreference3();
            }

            leaseRequest.setProposedHousing(proposedHousing);
            this.proposedHousing = proposedHousing;

            System.out.println("Check " + this.preference1);
            System.out.println("Check " + this.preference2);
            System.out.println("Check " + this.preference3);
            System.out.println("Check " + this.proposedHousing);
            return SUCCESS;
        }
    }

    public List<PotentialRoommate> getPotentialRoommates() {
        return potentialRoommates;
    }

    public void setPotentialRoommates(List<PotentialRoommate> potentialRoommates) {
        this.potentialRoommates = potentialRoommates;
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

    public LeaseRequest getLeaseRequest() {
        return leaseRequest;
    }

    public void setLeaseRequest(LeaseRequest leaseRequest) {
        this.leaseRequest = leaseRequest;
    }

    public LeasePreference getPreference1() {
        return preference1;
    }

    public void setPreference1(LeasePreference preference1) {
        this.preference1 = preference1;
    }

    public LeasePreference getPreference2() {
        return preference2;
    }

    public void setPreference2(LeasePreference preference2) {
        this.preference2 = preference2;
    }

    public LeasePreference getPreference3() {
        return preference3;
    }

    public void setPreference3(LeasePreference preference3) {
        this.preference3 = preference3;
    }

    public ProposedHousing getProposedHousing() {
        return proposedHousing;
    }

    public void setProposedHousing(ProposedHousing proposedHousing) {
        this.proposedHousing = proposedHousing;
    }
}
