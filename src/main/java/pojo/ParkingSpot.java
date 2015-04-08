package pojo;

import java.sql.Timestamp;
import java.util.Date;

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
    private Date permitStartDate;
    private Date permitEndDate;

    public Date getPermitStartDate() {
        return permitStartDate;
    }

    public void setPermitStartDate(Date permitStartDate) {
        this.permitStartDate = permitStartDate;
    }

    public Date getPermitEndDate() {
        return permitEndDate;
    }

    public void setPermitEndDate(Date permitEndDate) {
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
