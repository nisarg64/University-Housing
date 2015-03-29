package pojo;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class Guest {

    private String approvalId;
    private String name;
    private String status;
    private String addrStreet;
    private String addrCity;
    private String postalCode;
    private String gender;
    private String dob;
    private String primaryPhone;
    private String spclNeeds;

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSpclNeeds() {
        return spclNeeds;
    }

    public void setSpclNeeds(String spclNeeds) {
        this.spclNeeds = spclNeeds;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "approvalId='" + approvalId + '\'' +
                ", status='" + status + '\'' +
                ", addrStreet='" + addrStreet + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", primaryPhone='" + primaryPhone + '\'' +
                ", spclNeeds='" + spclNeeds + '\'' +
                '}';
    }
}
