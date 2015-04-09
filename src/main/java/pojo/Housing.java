package pojo;

/**
 * Author : abhishek
 * Created on 4/8/15.
 */
public class Housing {

    String housingId;
    String name;
    String type;
    String locationNumber;
    String aptId;

    public String getHousingId() {
        return housingId;
    }

    public void setHousingId(String housingId) {
        this.housingId = housingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "housingId='" + housingId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", locationNumber='" + locationNumber + '\'' +
                ", aptId='" + aptId + '\'' +
                '}';
    }
}
