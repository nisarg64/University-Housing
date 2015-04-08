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
    private boolean usePrivateAccommodation;
    private String housingId;
    private String locationNumber;
    private String housingType;
    private String housingName;
    private int housingRent;
    private int parkingRent;
    private int pendingCharge;
    private int lateFees;
    private String paymentMode; //Cheque or Cash
    private String earlyTerminationFees;
    private LeasePreference preference1;
    private LeasePreference preference2;
    private LeasePreference preference3;
    private boolean canApprove;


    public String getEarlyTerminationFees() {
        return earlyTerminationFees;
    }

    public void setEarlyTerminationFees(String earlyTerminationFees) {
        this.earlyTerminationFees = earlyTerminationFees;
    }

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

    public String getHousingId() {
        return housingId;
    }

    public void setHousingId(String housingId) {
        this.housingId = housingId;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public int getHousingRent() {
        return housingRent;
    }

    public void setHousingRent(int housingRent) {
        this.housingRent = housingRent;
    }

    public int getParkingRent() {
        return parkingRent;
    }

    public void setParkingRent(int parkingRent) {
        this.parkingRent = parkingRent;
    }

    public int getPendingCharge() {
        return pendingCharge;
    }

    public void setPendingCharge(int pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

    public int getLateFees() {
        return lateFees;
    }

    public void setLateFees(int lateFees) {
        this.lateFees = lateFees;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
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
                ", usePrivateAccommodation=" + usePrivateAccommodation +
                ", housingId='" + housingId + '\'' +
                ", locationNumber='" + locationNumber + '\'' +
                ", housingType='" + housingType + '\'' +
                ", housingName='" + housingName + '\'' +
                ", housingRent=" + housingRent +
                ", parkingRent=" + parkingRent +
                ", pendingCharge=" + pendingCharge +
                ", lateFees=" + lateFees +
                ", paymentMode='" + paymentMode + '\'' +
                ", preference1=" + preference1 +
                ", preference2=" + preference2 +
                ", preference3=" + preference3 +
                ", canApprove=" + canApprove +
                '}';
    }
}
