package pojo;

import oracle.sql.NUMBER;

/**
 * Created by nisarg on 4/3/15.
 */
public class ParkingSpot {
    private String spotId;
    private String lotId;
    private String spotType;
    private String availability;
    private Float rentalFee;
    private String permit_id;

    public String getPermit_id() {
        return permit_id;
    }

    public void setPermit_id(String permit_id) {
        this.permit_id = permit_id;
    }

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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Float getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Float rentalFee) {
        this.rentalFee = rentalFee;
    }
}
