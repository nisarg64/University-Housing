package pojo;

/**
 * Created by nisarg on 4/3/15.
 */
public class ParkingSpot {
    private String spotId;
    private String lotId;
    private String spotType;
    private Integer availability;
    private Float rentalFee;

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Float getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Float rentalFee) {
        this.rentalFee = rentalFee;
    }
}
