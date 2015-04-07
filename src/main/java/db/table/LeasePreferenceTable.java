package db.table;

import pojo.LeasePreference;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: Nikhil
 * Date: 03-04-15
 */
public class LeasePreferenceTable extends Table {

    public static final String TABLE_NAME = "LEASE_PREFERENCE";

    public static final String SEQUENCE_NUMBER = "sequence_number";
    public static final String TYPE = "type";

    public enum PreferenceType {
        Hall("Hall"),
        StudentApartment("Student Apartment"),
        FamilyApartment("Family Apartment");

        // String used to display this type on UI
        private String displayName;

        PreferenceType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
     /*   String query = "CREATE TABLE " + TABLE_NAME + " (" +
                LeaseTable.REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + ", " +
                SEQUENCE_NUMBER + " " + ColumnTypes.INTEGER_TYPE + ", " +
<<<<<<< Updated upstream
                TYPE + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + ", " +
                ResidentHallTable.HALL_ID + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + ", " +
                LeaseTable.PRIMARY_KEY_CONSTRAINT + "(" + LeaseTable.REQUEST_NUMBER + "," + SEQUENCE_NUMBER + "), " +
                LeaseTable.FOREIGN_KEY_CONSTRAINT + "(" + LeaseTable.REQUEST_NUMBER + ") " +
                LeaseTable.REFERENCES_STR + " " + LeaseTable.TABLE_NAME +
=======
                TYPE + " " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + ", " +
                ResidentHallTable.HALL_ID + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + ", " +
                LeaseTable.PRIMARY_KEY_CONSTRAINT + "(" + LeaseTable.REQUEST_NUMBER + "," + SEQUENCE_NUMBER + "), " +
                LeaseTable.FOREIGN_KEY_CONSTRAINT + "(" + LeaseTable.REQUEST_NUMBER + ") " +
                "housing_id" + " " + ColumnTypes.ID_TYPE + ", " +
                LeaseTable.PRIMARY_KEY_CONSTRAINT + "(" + LeaseTable.LEASE_NUMBER + "," + SEQUENCE_NUMBER + "), " +
                LeaseTable.FOREIGN_KEY_CONSTRAINT + "(" + LeaseTable.LEASE_NUMBER + ") " +
                LeaseTable.REFERENCES_STR + " " + LeaseTable.TABLE_NAME + ", " +
                LeaseTable.FOREIGN_KEY_CONSTRAINT + "(" + "housing_id" + ") " +
                LeaseTable.REFERENCES_STR + " " + "HOUSING" +
>>>>>>> Stashed changes
                ")";

        DBAccessor.executeQuery(conn, query);*/
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public void insert(Connection conn, LeasePreference preference) throws SQLException {
        String sql = createInsertPreparedStatement(TABLE_NAME, 0, LeaseTable.REQUEST_NUMBER, SEQUENCE_NUMBER, TYPE,
                ResidentHallTable.HALL_ID);
        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, preference.getLeaseNumber());
        stmt.setInt(2, preference.getSequenceNumber());
        stmt.setString(3, preference.getType());
        if (preference.getType().equalsIgnoreCase(PreferenceType.Hall.name())) {
            stmt.setString(4, preference.getHallId());
        } else {
            stmt.setString(4, null);
        }

        System.out.println(stmt);
        stmt.executeUpdate();
    }

}
