package action;

import db.table.InvoiceTable;
import db.table.LeaseRequestTable;
import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import db.view.InvoiceView;
import db.view.LeaseRequestView;
import db.view.LeaseTerminationRequestView;
import db.view.LeaseView;
import pojo.*;
import util.DBAccessor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/5/15.
 */
public class StaffLeaseAction extends UHAction{

    public static final String NO_PREFERENCES_SPECIFIED = "No Preferences Specified";
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
    private List<Lease> allLeases;

    public String getAllRequests() {
        allLeases = (new LeaseView()).viewOpenLeaseRequests(conn);
        return "success";
    }

    public String getAllLeaseTerminationRequests() {
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String editLeaseTerminationRequestToApprove() {
        LeaseTerminationRequestView view= new LeaseTerminationRequestView();
        System.out.println(requestNumber);
        LeaseTerminationRequest request = view.viewLeaseTerminationRequest(conn, requestNumber);
        System.out.println(request);
        if (request != null && request.getInspectionDate() != null) {
            return "exists";
        }

        return SUCCESS;
    }

    public String approveLeaseRequest() throws SQLException {
        LeaseRequest leaseRequest = (new LeaseRequestView().viewLeaseRequest(conn, requestNumber));
        if (!leaseRequest.isUsePrivateAccommodation()) {
            LeaseView leaseView = new LeaseView();
            ProposedHousing proposedHousing = leaseView.getProposedHousingForLease(conn, leaseRequest);
            lease = leaseView.viewLeaseFromLeaseRequest(conn, requestNumber);
            proposedHousing.getProposedHousingId();
            lease.setHousingId(proposedHousing.getProposedHousingId());
            lease.setLocationNumber(proposedHousing.getProposedLocationNumber());
            lease.setHousingName(proposedHousing.getProposedHousingName());
            LeaseTable table = new LeaseTable();
            table.approveLease(conn, lease);
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
        LeaseTerminationRequest updatedRequest = view.viewLeaseTerminationRequest(conn, requestNumber);
        String residentId = updatedRequest.getLease().getLeaseRequest().getResidentId();
        System.out.println("Damage Fees: " + damageFees);

        try {
            InvoiceView invoiceView = new InvoiceView();
            Invoice invoice = invoiceView.getInvoiceDetails(conn, residentId);
            // TODO add invoice changes
            invoice.setResidentId(residentId);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            invoice.setPaymentDate(sdf.format(updatedRequest.getLeaveDate()));
            invoice.setPaymentStatus("BILLED");
            invoice.setDueDate(sdf.format(updatedRequest.getLeaveDate()));
            invoice.setLateFees(Float.valueOf(0));
            InvoiceTable invoiceTable = new InvoiceTable();
            invoice.setOtherCharges(Float.valueOf(damageFees));
            invoice.setEarlyTerminationFees(Float.valueOf(0));
            invoice.setPaymentMethod("Cheque");
            sql = invoiceTable.generateInsertQuery(invoice);
            System.out.println(sql);
            DBAccessor.executeQuery(conn, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }


        allTerminationLeases = view.viewLeaseTerminationRequests(conn);
        return "success";
    }

    public String terminateLease() throws SQLException {
        LeaseTerminationRequestView view = new LeaseTerminationRequestView();
        LeaseTerminationRequest terminationRequest = view.viewLeaseTerminationRequest(conn, requestNumber);
        LeaseRequestTable requestTable = new LeaseRequestTable();
        String username = (String) sessionMap.get("username");
        username = username.trim();
        requestTable.updateStatusByStaff(conn, terminationRequest.getLease().getLeaseRequest().getRequestNumber(),
                LeaseTable.RequestStatus.Completed, username);
        LeaseTerminationRequestTable terminationRequestTable = new LeaseTerminationRequestTable();
        terminationRequestTable.updateStatusByStaff(conn, requestNumber, LeaseTable.RequestStatus.Completed, username);
        String residentId = terminationRequest.getLease().getLeaseRequest().getResidentId();
        String sql = "update invoice set payment_status = 'BILLED' where resident_id = " + residentId;
        DBAccessor.executeQuery(conn, sql);
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
        LeaseRequestView view = new LeaseRequestView();
        LeaseView leaseView = new LeaseView();
        lease = leaseView.viewLeaseFromLeaseRequest(conn, requestNumber);
        leaseRequest = lease.getLeaseRequest();
        view.populatePreferences(conn, leaseRequest);

        ProposedHousing proposedHousing = leaseView.getProposedHousingForLease(conn, leaseRequest);
        potentialRoommates = leaseView.getPotentialRoommates(conn, requestNumber);
        if (proposedHousing == null) {
            leaseRequest.setCanApprove(false);
            populatePreferences(leaseRequest);
            return "waiting";
        } else {
            leaseRequest.setCanApprove(true);
            leaseRequest.setProposedHousing(proposedHousing);
            this.proposedHousing = proposedHousing;
            populatePreferences(leaseRequest);
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
    private void populatePreferences(LeaseRequest leaseRequest) {
        LeaseRequestView view = new LeaseRequestView();
        view.populatePreferences(conn, leaseRequest);
        preference1= LeaseAction.getPreferenceDisplay(leaseRequest.getPreference1());
        preference2 = LeaseAction.getPreferenceDisplay(leaseRequest.getPreference2());
        preference3 = LeaseAction.getPreferenceDisplay(leaseRequest.getPreference3());
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

    public List<Lease> getAllLeases() {
        return allLeases;
    }

    public void setAllLeases(List<Lease> allLeases) {
        this.allLeases = allLeases;
    }
}
