package pojo;

import db.table.LeasePreferenceTable;

/**
 * User: Nikhil
 * Date: 03-04-15
 */
public class LeasePreference {

    private int sequenceNumber;
    private LeasePreferenceTable.PreferenceType preferenceType;
    private String hallId;
    private String hallName;

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public LeasePreferenceTable.PreferenceType getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(LeasePreferenceTable.PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
