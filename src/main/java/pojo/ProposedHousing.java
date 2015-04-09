package pojo;

/**
 * User: Nikhil
 * Date: 07-04-15
 */
public class ProposedHousing {

    // For Staff during lease approval
    private String proposedHousingId;
    private String proposedHousingName;
    private String proposedHousingType;
    private String proposedLocationNumber;
    private boolean usePrivateAccommodation;

    public String getProposedHousingId() {
        return proposedHousingId;
    }

    public void setProposedHousingId(String proposedHousingId) {
        this.proposedHousingId = proposedHousingId;
    }

    public String getProposedHousingName() {
        return proposedHousingName;
    }

    public void setProposedHousingName(String proposedHousingName) {
        this.proposedHousingName = proposedHousingName;
    }

    public String getProposedHousingType() {
        return proposedHousingType;
    }

    public void setProposedHousingType(String proposedHousingType) {
        this.proposedHousingType = proposedHousingType;
    }

    public String getProposedLocationNumber() {
        return proposedLocationNumber;
    }

    public void setProposedLocationNumber(String proposedLocationNumber) {
        this.proposedLocationNumber = proposedLocationNumber;
    }

    public boolean isUsePrivateAccommodation() {
        return usePrivateAccommodation;
    }

    public void setUsePrivateAccommodation(boolean usePrivateAccommodation) {
        this.usePrivateAccommodation = usePrivateAccommodation;
    }

    @Override
    public String toString() {
        return "ProposedHousing{" +
                "proposedHousingId='" + proposedHousingId + '\'' +
                ", proposedHousingName='" + proposedHousingName + '\'' +
                ", proposedHousingType='" + proposedHousingType + '\'' +
                ", proposedLocationNumber='" + proposedLocationNumber + '\'' +
                ", usePrivateAccommodation=" + usePrivateAccommodation +
                '}';
    }
}
