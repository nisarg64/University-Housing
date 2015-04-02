package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nisarg on 3/31/15.
 */
public class RequestParkingAction extends UHAction {
    private List<String> vehicleType;
    private List<String> isHandicapped;
    private List<String> nearbySpot;
    private String vehicle;
    private String handicapped;
    private String nearSpot;

    public String getHandicapped() {
        return handicapped;
    }

    public void setHandicapped(String handicapped) {
        this.handicapped = handicapped;
    }

    public String getNearSpot() {
        return nearSpot;
    }

    public void setNearSpot(String nearSpot) {
        this.nearSpot = nearSpot;
    }

    public List<String> getNearbySpot() {
        return nearbySpot;
    }

    public void setNearbySpot(List<String> nearbySpot) {
        this.nearbySpot = nearbySpot;
    }

    public List<String> getIsHandicapped() {
        return isHandicapped;
    }

    public void setIsHandicapped(List<String> isHandicapped) {
        this.isHandicapped = isHandicapped;
    }

    public List<String> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(List<String> vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public RequestParkingAction(){
        vehicleType = new ArrayList<String>();
        vehicleType.add("Bike");
        vehicleType.add("Compact Cars");
        vehicleType.add("Standard Cars");
        vehicleType.add("Large Cars");

        isHandicapped = new ArrayList<String>();
        isHandicapped.add("Yes");
        isHandicapped.add("No");

        nearbySpot = new ArrayList<String>();
        nearbySpot.add("Yes");
        nearbySpot.add("No");
    }


    public String execute() {
        return SUCCESS;
    }

    public String display() {
        return NONE;
    }
}
