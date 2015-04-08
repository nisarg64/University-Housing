package pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by nisarg on 4/3/15.
 */
public class Invoice {
    private String invoiceId;
    private String residentId;
    private Date enterDate;
    private Date leaveDate;
    private int housingRent;
    private int parkingRent;
    private int leaseNo;
    private String paymentOption;
    private Float otherCharges;
    private Float lateFees;
    private Date dueDate;
    private Float depositAmount;
    private String paymentStatus;
    private Date paymentDate;
    private Float amountPaid;
    private String paymentMethod;
    private Float earlyTerminationFees;
    private Float totalAmount;


    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
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

    public int getLeaseNo() {
        return leaseNo;
    }

    public void setLeaseNo(int leaseNo) {
        this.leaseNo = leaseNo;
    }

    public Float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Float pendingCharges) {
        this.otherCharges = pendingCharges;
    }

    public Float getLateFees() {
        return lateFees;
    }

    public void setLateFees(Float lateFees) {
        this.lateFees = lateFees;
    }


    public Float getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Float depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getEarlyTerminationFees() {
        return earlyTerminationFees;
    }

    public void setEarlyTerminationFees(Float earlyTerminationFees) {
        this.earlyTerminationFees = earlyTerminationFees;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
