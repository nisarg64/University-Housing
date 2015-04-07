package pojo;

import oracle.sql.NUMBER;

import java.sql.Timestamp;

/**
 * Created by nisarg on 4/3/15.
 */
public class ParkingSpot {
    private String spotId;
    private String lotId;
    private String spotType;
    private String availability;
    private Float rentalFee;
    private Integer permitId;
    private Timestamp permitStartDate;
    private Timestamp permitEndDate;

    public Timestamp getPermitStartDate() {
        return permitStartDate;
    }

    public void setPermitStartDate(Timestamp permitStartDate) {
        this.permitStartDate = permitStartDate;
    }

    public Timestamp getPermitEndDate() {
        return permitEndDate;
    }

    public void setPermitEndDate(Timestamp permitEndDate) {
        this.permitEndDate = permitEndDate;
    }

    public Integer getPermitId() {
        return permitId;
    }

    public void setPermitId(Integer permitId) {
        this.permitId = permitId;
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
