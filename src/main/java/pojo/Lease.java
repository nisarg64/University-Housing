package pojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class Lease {

    private int leaseNumber;
    private String residentId;
    private String status;
    private Date enterDate;
    private int duration;
    private Date leaveDate;
    private String paymentOption;
    private int securityDeposit;
    private Date cutoffDate;
    private List<LeasePreference> leasePreferences;

    public int getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(int leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public void setEnterDate(String enterDateStr) {
        this.enterDate = getDate(enterDateStr);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setLeaveDate(String leaveDateStr) {
        this.leaveDate = getDate(leaveDateStr);
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public int getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(int securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Date getCutoffDate() {
        return cutoffDate;
    }

    public void setCutoffDate(Date cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    public List<LeasePreference> getLeasePreferences() {
        return leasePreferences;
    }

    public void setLeasePreferences(List<LeasePreference> leasePreferences) {
        this.leasePreferences = leasePreferences;
    }

    public Date getDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("MM/DD/yy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static void main(String[] args) {
        Lease lease = new Lease();
        System.out.println(lease.getDate("10-15-1987"));
    }

    @Override
    public String toString() {
        return "Lease{" +
                "leaseNumber=" + leaseNumber +
                ", residentId='" + residentId + '\'' +
                ", status='" + status + '\'' +
                ", enterDate=" + enterDate +
                ", duration=" + duration +
                ", leaveDate=" + leaveDate +
                ", paymentOption='" + paymentOption + '\'' +
                ", securityDeposit=" + securityDeposit +
                ", cutoffDate=" + cutoffDate +
                '}';
    }
}
