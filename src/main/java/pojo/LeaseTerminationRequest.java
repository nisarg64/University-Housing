package pojo;

import util.Utils;

import java.util.Date;

/**
 * User: Nikhil
 * Date: 05-04-15
 */
public class LeaseTerminationRequest {

    private int requestNumner;
    private Lease lease;
    private String status;
    private String reason;
    private Date leaveDate;
    private Date inspectionDate;

    public int getRequestNumner() {
        return requestNumner;
    }

    public void setRequestNumner(int requestNumner) {
        this.requestNumner = requestNumner;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setLeaveDate(String leaveDateStr) {
        this.leaveDate = Utils.getDate(leaveDateStr);
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public void setInspectionDate(String inspectionDateStr) {
        this.inspectionDate = Utils.getDate(inspectionDateStr);
    }
}
