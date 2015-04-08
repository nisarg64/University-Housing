package pojo;

import java.sql.Timestamp;

/**
 * Created by nisarg on 4/3/15.
 */
public class Invoice {
    private String invoiceId;
    private String residentId;
    private int housingRent;
    private int parkingRent;
    private String leaseNo;
    private Float otherCharges;
    private Float lateFees;
    private Timestamp dueDate;
    private Float depositAmount;
    private String paymentStatus;
    private String invoicePaymentId;
    private Timestamp paymentDate;
    private Float amountPaid;
    private String paymentMethod;
    private Float earlyTerminationFees;
    private Float totalAmount;

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

    public String getLeaseNo() {
        return leaseNo;
    }

    public void setLeaseNo(String leaseNo) {
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

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
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

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
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
