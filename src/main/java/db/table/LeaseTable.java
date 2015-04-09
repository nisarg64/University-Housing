package db.table;

import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    // Constants for pre populated data
    public static final String LEASE_ID_1 = "1";
    public static final String LEASE_ID_2 = "2";
    public static final String LEASE_ID_3 = "3";
    public static final String LEASE_ID_4 = "4";
    public static final String LEASE_ID_5 = "5";
    public static final String LEASE_ID_6 = "6";
    public static final String LEASE_ID_7 = "7";
    public static final String LEASE_ID_8 = "8";
    public static final String LEASE_ID_9 = "9";

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

        // Sequence: LEASE_NUMBER, REQUEST_NUMBER, START_DATE, END_DATE, HOUSING_ID, LOCATION_NUMBER

        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_1, LeaseRequestTable.LEASE_REQUEST_ID_1,
                "to_date('01-AUG-2014', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));

/*        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_2, LeaseRequestTable.LEASE_REQUEST_ID_2,
                "to_date('01-AUG-2014', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));

        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_1, LeaseRequestTable.LEASE_REQUEST_ID_1,
                "to_date('01-JAN-2015', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));

        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_1, LeaseRequestTable.LEASE_REQUEST_ID_1,
                "to_date('01-JAN-2015', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));

        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_1, LeaseRequestTable.LEASE_REQUEST_ID_1,
                "to_date('01-JAN-2015', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));

        queries.add(createInsertQuery(TABLE_NAME, LEASE_ID_1, LeaseRequestTable.LEASE_REQUEST_ID_1,
                "to_date('01-JAN-2015', 'dd-MON-yyyy')", "to_date('31-JUL-2015', 'dd-MON-yyyy')", "'1'", "'001'"));*/
        /*queries.add(createInsertQuery(TABLE_NAME, "1", "'100540001'", "'" + RequestStatus.Completed + "'", "to_date('01-JAN-2014', 'dd-MON-yyyy')",
        "2", "'" + PaymentOption.Semester+ "'", "500", "'0'", "001", "'1'"));*/

        DBAccessor.executeBatchQuery(conn, queries);
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

    public int insert(Connection conn, Lease lease) throws SQLException {
        String query = "Select " + LEASE_SEQUENCE + ".NEXTVAL FROM DUAL";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int leaseNumber = rs.getInt(1);

        String sql = createInsertPreparedStatement(TABLE_NAME, 0, LEASE_NUMBER, LeaseRequestTable.REQUEST_NUMBER, START_DATE, END_DATE,
                HOUSING_ID, LOCATION_NUMBER);
        System.out.println(sql);
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, leaseNumber);
        stmt.setInt(2, lease.getLeaseRequest().getRequestNumber());
        stmt.setDate(3, new java.sql.Date(lease.getLeaseRequest().getEnterDate().getTime()));
        // TODO set dates based on duration
        stmt.setDate(4, new java.sql.Date(getEndDate(lease.getLeaseRequest().getDuration()).getTime()));
        stmt.setString(5, lease.getLeaseRequest().getProposedHousing().getProposedHousingId());
        stmt.setString(6, lease.getLeaseRequest().getProposedHousing().getProposedLocationNumber());
        System.out.println(stmt);
        stmt.executeUpdate();
        return leaseNumber;
        // TODO set updated by and updated on for request
        //sql = "UPDATE " + LeaseRequestTable.TABLE_NAME + " set " + LeaseRequestTable.STATUS + " = '" + RequestStatus.InProgress + "'";
        //DBAccessor.executeQuery(conn, sql);
    }

    private Date getEndDate(int duration) {
        /*Calendar calendar = Calendar.getInstance();
        if (duration == 1) {
            calendar.set(Calendar.MONTH, 12);
            calendar.set(Calendar.DATE, )
        }*/
        return new Date(System.currentTimeMillis() + 90000000);
    }

    public Lease getLease(String residentId){
        return new Lease();
    }
}