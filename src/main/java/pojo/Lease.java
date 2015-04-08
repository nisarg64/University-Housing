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
    private Date startDate;
    private Date endDate;
    private int duration;
    private Date leaveDate;
    private String paymentOption;
    private int securityDeposit;
    private Date cutoffDate;
    private boolean usePrivateAccommodation;
    private String housingId;
    private String housingType;
    private String housingName;
    private String locationNumber;
    private LeaseRequest leaseRequest;
    private int housingRent;
    private int parkingRent;
    private int pendingCharge;
    private int lateFees;
    private String paymentMode; //Cheque or Cash
    private String earlyTerminationFees;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEnterDate(String enterDateStr) {
        this.startDate = Utils.getDate(enterDateStr);
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

    public LeaseRequest getLeaseRequest() {
        return leaseRequest;
    }

    public void setLeaseRequest(LeaseRequest leaseRequest) {
        this.leaseRequest = leaseRequest;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "leaseNumber=" + leaseNumber +
                ", residentId='" + residentId + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", leaveDate=" + leaveDate +
                ", paymentOption='" + paymentOption + '\'' +
                ", securityDeposit=" + securityDeposit +
                ", cutoffDate=" + cutoffDate +
                ", usePrivateAccommodation=" + usePrivateAccommodation +
                ", housingId='" + housingId + '\'' +
                ", housingType='" + housingType + '\'' +
                ", housingName='" + housingName + '\'' +
                ", locationNumber='" + locationNumber + '\'' +
                ", housingRent=" + housingRent +
                ", parkingRent=" + parkingRent +
                ", pendingCharge=" + pendingCharge +
                ", lateFees=" + lateFees +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}
