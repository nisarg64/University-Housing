package db.table;

import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static final String REQUEST_SEQUENCE = "request_sequence";

    // Column Names
    public static final String REQUEST_NUMBER = "request_number";
    public static final String RES_ID = "res_id";
    public static final String STATUS = "status";
    public static final String ENTER_DATE = "enter_date";
    public static final String DURATION = "duration";
    public static final String PAYMENT_OPTION = "payment_option";
    public static final String SECURITY_DEPOSIT = "security_deposit";
    public static final String USE_PRIVATE_ACCOMMODATION = "use_private_accommodation";
    public static final String LOCATION_NUMBER = "location_number";
    public static final String HOUSING_ID = "housing_id";
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
                REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + "," +
                RES_ID + " " + ColumnTypes.ID_TYPE + " not null," +
                STATUS + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                ENTER_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                DURATION + " " + ColumnTypes.INTEGER_TYPE + " not null," +
                PAYMENT_OPTION + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                SECURITY_DEPOSIT + " " + ColumnTypes.NUMBER_TYPE + " not null," +
                USE_PRIVATE_ACCOMMODATION + " " + ColumnTypes.BOOLEAN_TYPE + " default '0' not null," +
                LOCATION_NUMBER + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + "," +
                HOUSING_ID + " " + ColumnTypes.ID_TYPE + "," +
                PRIMARY_KEY_CONSTRAINT + "(" + REQUEST_NUMBER + ")," +
                FOREIGN_KEY_CONSTRAINT + "(" + RES_ID + ") " + REFERENCES_STR + " " + "RESIDENT" + "," +
                FOREIGN_KEY_CONSTRAINT + "(" + HOUSING_ID + ") " + REFERENCES_STR + " Housing" +
        ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();

        // Sequence: REQUEST_NUMBER, RES_ID, STATUS, ENTER_DATE, DURATION, PAYMENT_OPTION,
        // SECURITY_DEPOSIT, USE_PRIVATE_ACCOMMODATION, LOCATION_NUMBER, HOUSING_ID

        queries.add(createInsertQuery(TABLE_NAME, "1", "'100540001'", "'" + RequestStatus.Completed + "'", "to_date('01-JAN-2014', 'dd-MON-yyyy')",
                "2", "'" + PaymentOption.Semester+ "'", "500", "'0'", "001", "'1'"));

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
        String sql = createInsertPreparedStatement(TABLE_NAME, 0, REQUEST_NUMBER, RES_ID, STATUS, ENTER_DATE,
                DURATION, PAYMENT_OPTION, SECURITY_DEPOSIT);
        sql = sql.replaceFirst("\\?", REQUEST_SEQUENCE + ".nextval");
        System.out.println();
        System.out.println(sql);
        System.out.println();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, lease.getResidentId());
        stmt.setString(2, lease.getStatus());
        stmt.setDate(3, new java.sql.Date(lease.getEnterDate().getTime()));
        stmt.setInt(4, lease.getDuration());
        //stmt.setDate(5, lease.getLeaveDate() == null ? null : new java.sql.Date(lease.getLeaveDate().getTime()));
        stmt.setString(5, lease.getPaymentOption());
        stmt.setInt(6, lease.getSecurityDeposit());
        System.out.println(stmt);
        stmt.executeUpdate();
        String query = "SELECT " + LeaseTable.REQUEST_SEQUENCE + ".CURRVAL FROM DUAL";
        stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public void updateStatus(Connection conn, Lease lease, LeaseTable.RequestStatus status) throws SQLException {
        String sql = "update " + TABLE_NAME + " set " + STATUS + " = '" + status +
            "' where " + REQUEST_NUMBER + " = " + lease.getLeaseNumber();;
        DBAccessor.executeQuery(conn, sql);
    }
}