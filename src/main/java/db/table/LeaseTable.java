package db.table;

import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 28-03-15
 */
public class LeaseTable extends Table {

    // Table Name
    public static final String TABLE_NAME = "LEASE";
    public static final String LEASE_SEQUENCE = "lease_sequence";

    // Column Names
    public static final String LEASE_NUMBER = "lease_number";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String HOUSING_ID = "housing_id";
    public static final String LOCATION_NUMBER = "location_no";
    public static final String PRIMARY_KEY_CONSTRAINT = "PRIMARY KEY";
    public static final String FOREIGN_KEY_CONSTRAINT = "FOREIGN KEY";
    public static final String REFERENCES_STR = "REFERENCES";

    public enum RequestStatus {
        Pending, Processed, InProgress, Completed, Cancelled, WaitList;
    }

    public enum PaymentOption {
        Monthly, Semester
    }

    public enum LeaseDuration {
        One(1),
        Two(2),
        Three(3);

        private int numberOfSemesters;

        LeaseDuration(int numberOfSemesters) {
            this.numberOfSemesters = numberOfSemesters;
        }

        public int getNumberOfSemesters() {
            return numberOfSemesters;
        }
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " (" +
                LEASE_NUMBER + " " + ColumnTypes.ID_INT_TYPE + "," +
                LeaseRequestTable.REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + " not null," +
                START_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                END_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                HOUSING_ID + " " + ColumnTypes.ID_TYPE + " not null," +
                LOCATION_NUMBER + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                PRIMARY_KEY_CONSTRAINT + "(" + LEASE_NUMBER + ")," +
                FOREIGN_KEY_CONSTRAINT + "(" + LeaseRequestTable.REQUEST_NUMBER + ") " + REFERENCES_STR + " " + LeaseRequestTable.TABLE_NAME + "," +
                FOREIGN_KEY_CONSTRAINT + "(" + HOUSING_ID + ") " + REFERENCES_STR + " Housing" +
        ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();

        // Sequence: LEASE_NUMBER, RES_ID, STATUS, START_DATE, DURATION, PAYMENT_OPTION,
        // SECURITY_DEPOSIT, USE_PRIVATE_ACCOMMODATION, LOCATION_NUMBER, HOUSING_ID

        //queries.add(createInsertQuery(TABLE_NAME, "1", "'100540001'", "'" + RequestStatus.InProgress + "'", "to_date('01-JAN-2015', 'dd-MON-yyyy')",
        /*queries.add(createInsertQuery(TABLE_NAME, "1", "'100540001'", "'" + RequestStatus.Completed + "'", "to_date('01-JAN-2014', 'dd-MON-yyyy')",
                "2", "'" + PaymentOption.Semester+ "'", "500", "'0'", "001", "'1'"));

        DBAccessor.executeBatchQuery(conn, queries);*/
    }

    public StringBuilder startInsertQuery(String tableName) {
        return new StringBuilder("INSERT INTO ").append(tableName);
    }

    public StringBuilder addColumnsToInsertQuery(StringBuilder str, String... columns) {
        str.append(" (");
        for (int i = 1; i <= columns.length; ++i) {
            str.append(columns[i - 1]);
            if (i < columns.length) {
                str.append(", ");
            }
        }
        str.append(")");
        return str;
    }

    public StringBuilder addValuesToInsertQuery(StringBuilder str, String... values) {
        str.append(" VALUES(");
        for (int i = 1; i <= values.length; ++i) {
            str.append(values[i - 1]);
            if (i < values.length) {
                str.append(", ");
            }
        }
        str.append(")");
        return str;
    }

    public void insert(Connection conn, Lease lease) throws SQLException {
        String sql = createInsertPreparedStatement(TABLE_NAME, 0, LEASE_NUMBER, LeaseRequestTable.REQUEST_NUMBER, START_DATE, END_DATE,
                HOUSING_ID, LOCATION_NUMBER);
        sql = sql.replaceFirst("\\?", LEASE_SEQUENCE + ".nextval");
        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, lease.getLeaseRequest().getRequestNumber());
        stmt.setString(2, lease.getStatus());
        // TODO set dates based on duration
        stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
        stmt.setDate(4, new java.sql.Date(System.currentTimeMillis() + 9999999));
        stmt.setString(5, lease.getLeaseRequest().getProposedHousing().getProposedHousingId());
        stmt.setString(6, lease.getLeaseRequest().getProposedHousing().getProposedLocationNumber());
        System.out.println(stmt);
        stmt.executeUpdate();
        // TODO set updated by and updated on for request
        //sql = "UPDATE " + LeaseRequestTable.TABLE_NAME + " set " + LeaseRequestTable.STATUS + " = '" + RequestStatus.InProgress + "'";
        //DBAccessor.executeQuery(conn, sql);
    }

    public Lease getLease(String residentId){
        return new Lease();
    }
}