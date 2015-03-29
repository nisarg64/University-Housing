package pojo;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class Student {

    private String studentId;
    private String name;
    private String category;
    private String addrStreet;
    private String addrCity;
    private String postalCode;
    private String gender;
    private String course;
    private String dob;
    private String primaryPhone;
    private String spclNeeds;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", addrStreet='" + addrStreet + '\'' +
                ", addrCity='" + addrCity + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", gender='" + gender + '\'' +
                ", course='" + course + '\'' +
                ", dob='" + dob + '\'' +
                ", primaryPhone='" + primaryPhone + '\'' +
                ", spclNeeds='" + spclNeeds + '\'' +
                '}';
    }
}
