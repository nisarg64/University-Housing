package action;

import pojo.Lease;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/5/15.
 */
public class StaffLeaseAction extends UHAction{

    private List<Lease> allLeases;
    private List<Lease> allTerminationLeases;
    private int leaseNumber;
    private Lease lease;

    public String getAllRequests() {
        return "success";
    }

    public String getAllTerminationRequests() {
        return "success";
    }

    public String approveLease(){
        return "success";
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

    public List<Lease> getAllTerminationLeases() {
        return allTerminationLeases;
    }

    public void setAllTerminationLeases(List<Lease> allTerminationLeases) {
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
