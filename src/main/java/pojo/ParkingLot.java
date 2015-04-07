package pojo;

/**
 * Created by nisarg on 4/2/15.
 */
public class ParkingLot {

    private String lotId;
    private String lotType;
    private String nearbyHousing;
    private String address;
    private String housingType;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getNearbyHousing() {
        return nearbyHousing;
    }

    public void setNearbyHousing(String nearbyHousing) {
        this.nearbyHousing = nearbyHousing;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }
}
