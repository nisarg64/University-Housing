package pojo;

import util.Utils;

import java.util.Date;

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
    private String placeNumber;
    private String apartmentNumber;
    private boolean usePrivateAccommodation;

    private LeasePreference preference1;
    private LeasePreference preference2;
    private LeasePreference preference3;

    private boolean canApprove;

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
        this.enterDate = Utils.getDate(enterDateStr);
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
        this.leaveDate = Utils.getDate(leaveDateStr);
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

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public boolean getCanApprove() {
        return canApprove;
    }

    public void setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
    }

    public boolean isUsePrivateAccommodation() {
        return usePrivateAccommodation;
    }

    public void setUsePrivateAccommodation(boolean usePrivateAccommodation) {
        this.usePrivateAccommodation = usePrivateAccommodation;
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
