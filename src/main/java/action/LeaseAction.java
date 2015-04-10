package action;

import db.table.*;
import db.view.LeaseRequestView;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.*;
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

    private LeasePreference leasePreference1;
    private LeasePreference leasePreference2;
    private LeasePreference leasePreference3;

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
    private String message = "";
    private List<Housing> allVacancies;

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
        if(lease == null){
             lease = new Lease();
              lease.setStatus("");
             lease.setLocationNumber("");
             lease.setPaymentOption("");
             message = "No Current Lease Found";
        }
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

    public String viewLeaseRequest() {

        System.out.println(".................");
        System.out.println(requestNumber);
        System.out.println(".................");
        LeaseRequestView view = new LeaseRequestView();
        leaseRequest = view.viewLeaseRequest(conn, requestNumber);
        return SUCCESS;
    }

    public String newLeaseRequest() {

        String username = (String) sessionMap.get("username");
        System.out.println(leaseRequest);

        leaseRequest = (new LeaseRequestView()).viewCurrentLeaseRequest(conn, username);
        if (leaseRequest != null) {
            if(leaseRequest.getPreference1() == null){
                leasePreference1 = new LeasePreference();
                leasePreference1.setType("No Preference Specified");
                leasePreference1.setHallName("No Preference Specified");
            }else {
                leasePreference1 = leaseRequest.getPreference1();
            }

            if(leaseRequest.getPreference2() == null){
                leasePreference2 = new LeasePreference();
                leasePreference2.setType("No Preference Specified");
                leasePreference2.setHallName("No Preference Specified");
            }else {
                leasePreference2 = leaseRequest.getPreference2();
            }

            if(leaseRequest.getPreference3() == null){
                leasePreference3 = new LeasePreference();
                leasePreference3.setType("No Preference Specified");
                leasePreference3.setHallName("No Preference Specified");
            }else {
                leasePreference3 = leaseRequest.getPreference3();
            }

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

        String username = (String) sessionMap.get("username");
        leaseRequest.setResidentId(username.trim());
        leaseRequest.setStatus(LeaseTable.RequestStatus.Pending.name());
        leaseRequest.setRequestNumber(new LeaseRequestTable().insert(conn, leaseRequest));
        System.out.println(leaseRequest);
        if(!leaseRequest.isUsePrivateAccommodation()){
            System.out.println(leasePreference1);
            int sequenceNumber = 1;
            sequenceNumber = insertPreference(sequenceNumber, leasePreference1, leaseRequest);
            sequenceNumber = insertPreference(sequenceNumber, leasePreference2, leaseRequest);
            sequenceNumber = insertPreference(sequenceNumber, leasePreference3, leaseRequest);
            System.out.println(sequenceNumber);
        }
        LeaseRequestView view = new LeaseRequestView();
        view.populatePreferences(conn, leaseRequest);
        leasePreference1 = leaseRequest.getPreference1();
        leasePreference2 = leaseRequest.getPreference2();
        leasePreference3 = leaseRequest.getPreference3();
        System.out.println(leaseRequest);
        return SUCCESS;
    }

    private int insertPreference(int sequenceNumber, LeasePreference pref, LeaseRequest leaseRequest) {
        System.out.println(pref);
        if (pref != null && !pref.getType().equals(-1)) {
            try {
                pref.setSequenceNumber(sequenceNumber++);
                pref.setRequestNumber(leaseRequest.getRequestNumber());
                if (pref.getHallId().equals(-1)) {
                    pref.setHallId(null);
                }
                new LeasePreferenceTable().insert(conn, pref);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sequenceNumber;
    }

    public String getLeaseDetail(){
        System.out.println("leaseNumber" + leaseNumber);
        return SUCCESS;
    }

    public String getAllLeases() {

        String username = (String) sessionMap.get("username");
        leaseRequests = (new LeaseRequestView()).viewAllLeaseRequestsForResident(conn, username);
        System.out.println(leaseRequests.size());
        terminateLeases = (new LeaseTerminationRequestView()).viewAllLeaseTerminationRequestForResident(conn, username.trim());
        System.out.println(terminateLeases);
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

        LeaseRequest leaseRequest = new LeaseRequestView().viewLeaseRequest(conn, requestNumber);
        if (leaseRequest != null) {
            System.out.println(leaseRequest);
            if (username.equals(leaseRequest.getResidentId().trim())) {
                LeaseTable.RequestStatus status = LeaseTable.RequestStatus.valueOf(leaseRequest.getStatus());
                switch (status) {
                    case Pending:
                    case WaitList:
                        (new LeaseRequestTable()).updateStatus(conn, requestNumber, LeaseTable.RequestStatus.Cancelled);
                        message = "Lease Cancel Request Submitted Successfully";
                        return SUCCESS;
                    default:
                        message = " Status: Cannot Update";
                        return CANNOTUPDATE;
                }
            } else {
                message = "Some Error Occurred : Request Id doesn't Exist";
                return NOTEXISTS;
            }

        }
        LeaseTerminationRequest request = new LeaseTerminationRequestView().viewLeaseTerminationRequest(conn, requestNumber);
        if (request != null) {
            System.out.println(request);
            if (username.equals(request.getLease().getResidentId().trim())) {
                if (LeaseTable.RequestStatus.Pending.name().equals(request.getStatus())) {
                    (new LeaseTerminationRequestTable()).updateStatus(conn, request, LeaseTable.RequestStatus.Cancelled);
                    message = "Lease Cancel Request Submitted Successfully";
                    return SUCCESS;
                } else {
                    message = " Status: Cannot Update";
                    return CANNOTUPDATE;
                }
            } else {
                message = "Some Error Occurred : Request Id doesn't Exist";
                return NOTEXISTS;
            }
        }
        message = "Some Error Occurred : Request Id doesn't Exist";
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

    public String fetchAllVacancies(){

        allVacancies = LeaseUtils.getAllVacancies(conn);
        return "success";
    }

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

    public LeasePreference getLeasePreference1() {
        return leasePreference1;
    }

    public void setLeasePreference1(LeasePreference leasePreference1) {
        this.leasePreference1 = leasePreference1;
    }

    public LeasePreference getLeasePreference2() {
        return leasePreference2;
    }

    public void setLeasePreference2(LeasePreference leasePreference2) {
        this.leasePreference2 = leasePreference2;
    }

    public LeasePreference getLeasePreference3() {
        return leasePreference3;
    }

    public void setLeasePreference3(LeasePreference leasePreference3) {
        this.leasePreference3 = leasePreference3;
    }

    public List<LeaseRequest> getLeaseRequests() {
        return leaseRequests;
    }

    public void setLeaseRequests(List<LeaseRequest> leaseRequests) {
        this.leaseRequests = leaseRequests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAllVacancies(List<Housing> allVacancies) {
        this.allVacancies = allVacancies;
    }

    public List<Housing> getAllVacancies() {
        return allVacancies;
    }
}
