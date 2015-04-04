package action;

import db.table.LeasePreferenceTable;
import db.table.LeaseTable;
import db.view.LeaseView;
import pojo.Lease;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseAction extends UHAction {

    private Lease lease;
    private List<Integer> leaseDurations;
    private List<String> paymentOptions;
    private List<String> preferenceTypes;

    public LeaseAction() {}

    public String viewCurrentLease() {
        LeaseView view = new LeaseView();
        String username = (String) sessionMap.get("username");
        System.out.println("Resident Id" + username);
        if (username == null) {
            return ERROR;
        }
        lease = view.viewCurrentLease(conn, username);
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
            preferenceTypes.add(preferenceType.name());
        }
        return SUCCESS;
    }

    public String createLeaseRequest() throws Exception {
        System.out.println(lease);
        String username = (String) sessionMap.get("username");
        lease.setResidentId(username);
        lease.setSecurityDeposit(0);
        lease.setStatus(LeaseTable.LeaseStatus.Pending.name());
        new LeaseTable().insert(conn, lease);
        return SUCCESS;
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
}
