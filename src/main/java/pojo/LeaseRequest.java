package pojo;

import java.util.Date;

/**
 * User: Nikhil
 * Date: 08-04-15
 */
public class LeaseRequest {

    private int requestNumber;
    private String residentId;
    private String status;
    private Date enterDate;
    private int duration;
    private String paymentOption;
    private boolean usePrivateAccommodation;
    private String updatedBy;
    private Date updatedOn;

    private boolean hasPreference;
    private LeasePreference preference1;
    private LeasePreference preference2;
    private LeasePreference preference3;

    private boolean canApprove;
    private ProposedHousing proposedHousing;

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public boolean isUsePrivateAccommodation() {
        return usePrivateAccommodation;
    }

    public void setUsePrivateAccommodation(boolean usePrivateAccommodation) {
        this.usePrivateAccommodation = usePrivateAccommodation;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isHasPreference() {
        return hasPreference;
    }

    public void setHasPreference(boolean hasPreference) {
        this.hasPreference = hasPreference;
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

    public boolean isCanApprove() {
        return canApprove;
    }

    public void setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
    }

    public ProposedHousing getProposedHousing() {
        return proposedHousing;
    }

    public void setProposedHousing(ProposedHousing proposedHousing) {
        this.proposedHousing = proposedHousing;
    }



    @Override
    public String toString() {
        return "LeaseRequest{" +
                "requestNumber=" + requestNumber +
                ", residentId='" + residentId + '\'' +
                ", status='" + status + '\'' +
                ", enterDate=" + enterDate +
                ", duration=" + duration +
                ", paymentOption='" + paymentOption + '\'' +
                ", usePrivateAccommodation=" + usePrivateAccommodation +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
