package pojo;

/**
 * Author : abhishek
 * Created on 4/3/15.
 */
public class Staff {

    private String staffId;
    private String fname;
    private String lname;
    private String status;
    private String addrStreet;
    private String addrCity;
    private String postalCode;
    private String gender;
    private String dob;
    private String primaryPhone;
    private String spclNeeds;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
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
