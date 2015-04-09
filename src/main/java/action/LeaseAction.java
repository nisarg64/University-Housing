package action;

import db.table.*;
import db.view.LeaseRequestView;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.Lease;
import pojo.LeasePreference;
import pojo.LeaseRequest;
import pojo.LeaseTerminationRequest;
import util.DBAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseAction extends UHAction {

    private LeaseRequest leaseRequest;
    public static final String NOTEXISTS = "notexists";
    public static final String CANNOTUPDATE = "cannotupdate";
    private Lease lease;
    private int leaseNumber;
    private List<Integer> leaseDurations;
    private List<String> paymentOptions;
    private List<String> preferenceTypes;
    private Map<String, String> halls;
    private List<Lease> leases;
    private List<LeaseRequest> leaseRequests;
    private List<LeaseTerminationRequest> terminateLeases;
    private LeaseTerminationRequest leaseTerminationRequest;
    private int requestNumber;

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

        String username = (String) sessionMap.get("username");

        leaseRequest = (new LeaseRequestView()).viewCurrentLeaseRequest(conn, username);
        if (leaseRequest != null) {
            return "exists";
        }

        paymentOptions = new ArrayList<String>();
        for (LeaseTable.PaymentOption option : LeaseTable.PaymentOption.values()) {
            paymentOptions.add(option.name());
        }

        populateLeaseDurations();

        preferenceTypes = new ArrayList<String>();
        for (LeasePreferenceTable.PreferenceType preferenceType : LeasePreferenceTable.PreferenceType.values()) {
                preferenceTypes.add(preferenceType.getDisplayName());
        }

        halls = new HashMap<String, String>();
        String query = "Select hall." + ResidentHallTable.HALL_ID + ", h.name from " +
                ResidentHallTable.TABLE_NAME + " hall inner join housing h on h.housing_id = hall." + ResidentHallTable.HALL_ID +
                ", resident r where (r.res_id = " + username.trim() + ") and (hall.student_type is null or hall.student_type = r.category)";

        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                halls.put(rs.getString(ResidentHallTable.HALL_ID), rs.getString("name"));
            }
        } catch (SQLException ex){
            System.err.println("Error Occurred During View Halls " + ex.getMessage());
        }
        return SUCCESS;
    }

    private void populateLeaseDurations() {
        leaseDurations = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        int currMonth = calendar.get(Calendar.MONTH);
        if (currMonth <= 4) {
            leaseDurations.add(2);
            leaseDurations.add(3);
        } else if (currMonth <= 6) {
            leaseDurations.add(3);
        } else {
            leaseDurations.add(1);
            leaseDurations.add(2);
            leaseDurations.add(3);
        }
    }

    public String createLeaseRequest() throws Exception {
        System.out.println(leaseRequest);
        String username = (String) sessionMap.get("username");
        leaseRequest.setResidentId(username.trim());
        leaseRequest.setStatus(LeaseTable.RequestStatus.Pending.name());
        leaseRequest.setRequestNumber(new LeaseRequestTable().insert(conn, leaseRequest));

         LeasePreference pref = leaseRequest.getPreference1();
        pref.setRequestNumber(leaseRequest.getRequestNumber());
        pref.setSequenceNumber(1);
        new LeasePreferenceTable().insert(conn, pref);

        pref = leaseRequest.getPreference2();
        pref.setRequestNumber(leaseRequest.getRequestNumber());
        pref.setSequenceNumber(2);
        new LeasePreferenceTable().insert(conn, pref);

        pref = leaseRequest.getPreference3();
        pref.setRequestNumber(leaseRequest.getRequestNumber());
        pref.setSequenceNumber(3);
        new LeasePreferenceTable().insert(conn, pref);

        return SUCCESS;
    }

    public String getLeaseDetail(){
        System.out.println("leaseNumber" + leaseNumber);
        return SUCCESS;
    }

    public String getAllLeases() {

        String username = (String) sessionMap.get("username");
        leaseRequests = (new LeaseRequestView()).viewAllLeaseRequestsForResident(conn, username);
        terminateLeases = (new LeaseTerminationRequestView()).viewAllLeaseTerminationRequestForResident(conn, username.trim());
        return "success";
    }

    public String newLeaseTerminationRequest() {
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        String username = (String) sessionMap.get("username");
        leaseTerminationRequest = view.viewOpenLeaseTerminationRequestForResident(conn, username);
        if (leaseTerminationRequest != null) {
            return "exists";
        }
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
        leaseTerminationRequest.setStatus(LeaseTable.RequestStatus.Pending.name());
        new LeaseTerminationRequestTable().insert(conn, leaseTerminationRequest);
        return SUCCESS;
    }

    public String cancelRequest() {
        return SUCCESS;
    }

    public String saveCancelRequest() throws SQLException {
        System.out.println(requestNumber);
        String username = ((String) sessionMap.get("username")).trim();

        Lease lease = new LeaseView().viewLease(conn, requestNumber);
        if (lease != null) {
            System.out.println(lease);
            if (username.equals(lease.getResidentId().trim())) {
                LeaseTable.RequestStatus status = LeaseTable.RequestStatus.valueOf(lease.getStatus());
                switch (status) {
                    case Pending:
                    case WaitList:
                        (new LeaseRequestTable()).updateStatus(conn, lease.getLeaseRequest().getRequestNumber(), LeaseTable.RequestStatus.Cancelled);
                        return SUCCESS;
                    default:
                        return CANNOTUPDATE;
                }
            } else {
                return NOTEXISTS;
            }

        }
        LeaseTerminationRequest request = new LeaseTerminationRequestView().viewLeaseTerminationRequest(conn, requestNumber);
        if (request != null) {
            System.out.println(request);
            if (username.equals(request.getLease().getResidentId().trim())) {
                if (LeaseTable.RequestStatus.Pending.name().equals(request.getStatus())) {
                    (new LeaseTerminationRequestTable()).updateStatus(conn, request, LeaseTable.RequestStatus.Cancelled);
                    return SUCCESS;
                } else {
                    return CANNOTUPDATE;
                }
            } else {
                return NOTEXISTS;
            }
        }
        return NOTEXISTS;
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

    public List<LeaseTerminationRequest> getTerminateLeases() {
        return terminateLeases;
    }

    public void setTerminateLeases(List<LeaseTerminationRequest> terminateLeases) {
        this.terminateLeases = terminateLeases;
    }

    public LeaseTerminationRequest getLeaseTerminationRequest() {
        return leaseTerminationRequest;
    }

    public void setLeaseTerminationRequest(LeaseTerminationRequest leaseTerminationRequest) {
        this.leaseTerminationRequest = leaseTerminationRequest;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public LeaseRequest getLeaseRequest() {
        return leaseRequest;
    }

    public void setLeaseRequest(LeaseRequest leaseRequest) {
        this.leaseRequest = leaseRequest;
    }
}
