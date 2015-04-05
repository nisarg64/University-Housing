package action;

import db.table.LeasePreferenceTable;
import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import db.table.ResidentHallTable;
import db.view.LeaseView;
import pojo.Lease;
import pojo.LeasePreference;
import pojo.LeaseTerminationRequest;
import util.DBAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseAction extends UHAction {

    private Lease lease;
    private int leaseNumber;
    private List<Integer> leaseDurations;
    private List<String> paymentOptions;
    private List<String> preferenceTypes;
    private Map<String, String> halls;
    private List<Lease> leases;
    private List<Lease> terminateLeases;
    private LeaseTerminationRequest leaseTerminationRequest;

    public LeaseAction() {}

    public String viewFormerLeases() {
        String username = (String) sessionMap.get("username");
        if (username == null) {
            return ERROR;
        }
        LeaseView view = new LeaseView();
        leases = view.viewFormerLeases(conn, username);

        return SUCCESS;
    }

    public String viewCurrentLease() {
        LeaseView view = new LeaseView();
        String username = (String) sessionMap.get("username");
        if (username == null) {
            return ERROR;
        }
        lease = view.viewCurrentLease(conn, username);
        return SUCCESS;
    }

    public String viewLease() {

        System.out.println(".................");
        System.out.println(leaseNumber);
        System.out.println(".................");
        LeaseView view = new LeaseView();
        lease = view.viewLease(conn, leaseNumber);
        return SUCCESS;
    }

    public String newLeaseRequest() {
        //String username = (String) sessionMap.get("username");
        //leaseDurations.
        lease = new Lease();
        paymentOptions = new ArrayList<String>();
        for (LeaseTable.PaymentOption option : LeaseTable.PaymentOption.values()) {
            paymentOptions.add(option.name());
        }
        leaseDurations = new ArrayList<Integer>();
        for (LeaseTable.LeaseDuration duration : LeaseTable.LeaseDuration.values()) {
            leaseDurations.add(duration.getNumberOfSemesters());
        }

        preferenceTypes = new ArrayList<String>();
        for (LeasePreferenceTable.PreferenceType preferenceType : LeasePreferenceTable.PreferenceType.values()) {
                preferenceTypes.add(preferenceType.getDisplayName());
        }

        halls = new HashMap<String, String>();
        String query = "Select " + ResidentHallTable.HALL_ID + ", " + ResidentHallTable.HALL_NAME + " from " +
                ResidentHallTable.TABLE_NAME;

        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                halls.put(rs.getString(ResidentHallTable.HALL_ID), rs.getString(ResidentHallTable.HALL_NAME));
            }
        } catch (SQLException ex){
            System.err.println("Error Occurred During View Lease " + ex.getMessage());
        }
        return SUCCESS;
    }

    public String createLeaseRequest() throws Exception {
        System.out.println(lease);
        String username = (String) sessionMap.get("username");
        lease.setResidentId(username);
        lease.setSecurityDeposit(0);
        lease.setStatus(LeaseTable.LeaseStatus.Pending.name());
        lease.setLeaseNumber(new LeaseTable().insert(conn, lease));

        LeasePreference pref = lease.getPreference1();
        pref.setLeaseNumber(lease.getLeaseNumber());
        pref.setSequenceNumber(1);
        new LeasePreferenceTable().insert(conn, pref);

        pref = lease.getPreference2();
        pref.setLeaseNumber(lease.getLeaseNumber());
        pref.setSequenceNumber(2);
        new LeasePreferenceTable().insert(conn, pref);

        pref = lease.getPreference3();
        pref.setLeaseNumber(lease.getLeaseNumber());
        pref.setSequenceNumber(3);
        new LeasePreferenceTable().insert(conn, pref);

        return SUCCESS;
    }

    public String getLeaseDetail(){
        System.out.println("leaseNumber" + leaseNumber);
        return SUCCESS;
    }

    public String getAllLeases() {

        return "success";
    }

    public String newLeaseTerminationRequest() {
        return SUCCESS;
    }

    public String createLeaseTerminationRequest() throws SQLException {
        LeaseView view = new LeaseView();
        String username = (String) sessionMap.get("username");
        if (username == null) {
            return ERROR;
        }
        Lease lease = view.viewCurrentLease(conn, username);
        leaseTerminationRequest.setLease(lease);
        leaseTerminationRequest.setStatus(LeaseTerminationRequestTable.LeaseTerminationRequestStatus.Pending.name());
        new LeaseTerminationRequestTable().insert(conn, leaseTerminationRequest);
        return SUCCESS;
    }

    /*public Lease getLease() throws Exception {
        *//*System.out.println(lease);
        String username = (String) sessionMap.get("username");
        lease.setResidentId(username);
        lease.setSecurityDeposit(0);
        lease.setStatus(LeaseTable.LeaseStatus.Pending.name());
        new LeaseTable().insert(conn, lease);*//*
        return null;
    }*/

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public List<Integer> getLeaseDurations() {
        return leaseDurations;
    }

    public void setLeaseDurations(List<Integer> leaseDurations) {
        this.leaseDurations = leaseDurations;
    }

    public List<String> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public List<String> getPreferenceTypes() {
        return preferenceTypes;
    }

    public void setPreferenceTypes(List<String> preferenceTypes) {
        this.preferenceTypes = preferenceTypes;
    }

    public Map<String, String> getHalls() {
        return halls;
    }

    public void setHalls(Map<String, String> halls) {
        this.halls = halls;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public int getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(int leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public List<Lease> getTerminateLeases() {
        return terminateLeases;
    }

    public void setTerminateLeases(List<Lease> terminateLeases) {
        this.terminateLeases = terminateLeases;
    }

    public LeaseTerminationRequest getLeaseTerminationRequest() {
        return leaseTerminationRequest;
    }

    public void setLeaseTerminationRequest(LeaseTerminationRequest leaseTerminationRequest) {
        this.leaseTerminationRequest = leaseTerminationRequest;
    }
}
