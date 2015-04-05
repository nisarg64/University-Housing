package db.table;

import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    public static final String RES_ID = "res_id";
    public static final String STATUS = "status";
    public static final String ENTER_DATE = "enter_date";
    public static final String DURATION = "duration";
    public static final String LEAVE_DATE = "leave_date";
    public static final String PAYMMENT_OPTION = "payment_option";
    public static final String SECURITY_DEPOSIT = "security_deposit";
    public static final String CUTOFF_DATE = "cutoff_date";
    public static final String HAS_PRIVATE_ACCOMMODATION = "has_private_accommodation";
    public static final String PRIMARY_KEY_CONSTRAINT = "PRIMARY KEY";
    public static final String FOREIGN_KEY_CONSTRAINT = "FOREIGN KEY";
    public static final String REFERENCES_STR = "REFERENCES";

    public enum LeaseStatus {
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

        /*StringBuilder statusValues = new StringBuilder("(");
        for (LeaseStatus status : LeaseStatus.values()) {
            statusValues.append("'");
            statusValues.append(status.toString());
            statusValues.append("',");
        }
        statusValues.setCharAt(statusValues.length() - 1, ')');*/

        String query = "CREATE TABLE " + getTableName() + " (" +
                LEASE_NUMBER + " " + ColumnTypes.INTEGER_TYPE + "," +
                RES_ID + " " + ColumnTypes.ID_TYPE + " not null," +
                STATUS + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                ENTER_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                // TODO academic year
                DURATION + " " + ColumnTypes.INTEGER_TYPE + " not null," +
                LEAVE_DATE + " " + ColumnTypes.DATE_TYPE + "," +
                PAYMMENT_OPTION + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                SECURITY_DEPOSIT + " " + ColumnTypes.NUMBER_TYPE + " not null," +
                CUTOFF_DATE + " " + ColumnTypes.DATE_TYPE + "," +
                HAS_PRIVATE_ACCOMMODATION + " " + ColumnTypes.BOOLEAN_TYPE + " default '0' not null," +
                RoomTable.PLACE_NUM + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + "," +
                ApartmentTable.APARTMENT_NO + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + "," +
                PRIMARY_KEY_CONSTRAINT + "(" + LEASE_NUMBER + ")," +
                FOREIGN_KEY_CONSTRAINT + "(" + RoomTable.PLACE_NUM + ") " + REFERENCES_STR + " " + RoomTable.TABLE_NAME + "," +
                FOREIGN_KEY_CONSTRAINT + "(" + ApartmentTable.APARTMENT_NO + ") " + REFERENCES_STR + " " + ApartmentTable.TABLE_NAME +
                /*"CHECK (" + STATUS + " IN " + statusValues.toString() + ")," +
                "CHECK (" + DURATION + " IN (1, 2, 3))," +*/
        ")";
        System.out.println(query);

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES(1, 'akagrawa', '" + LeaseStatus.Pending + "'," +
                "sysdate, 2, null, '" + PaymentOption.Monthly + "', 500, sysdate + 30, '0', null, null)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES(2, 'abora', '" + LeaseStatus.Processed +"'," +
                "sysdate, 3, null, '" + PaymentOption.Monthly + "', 1000, sysdate + 90, '0', null, null)";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }

    public StringBuilder addColumnOrValue(StringBuilder str, String columnOrValue) {
        str.append(columnOrValue).append(", ");
        return str;
    }



    public int insert(Connection conn, Lease lease) throws SQLException {
        String sql = createInsertSql(TABLE_NAME, 0, LEASE_NUMBER, RES_ID, STATUS, ENTER_DATE,
                DURATION, LEAVE_DATE, PAYMMENT_OPTION ,SECURITY_DEPOSIT);
        sql = sql.replaceFirst("\\?", LEASE_SEQUENCE + ".nextval");
        System.out.println();
        System.out.println(sql);
        System.out.println();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, lease.getResidentId());
        stmt.setString(2, lease.getStatus());
        stmt.setDate(3, new java.sql.Date(lease.getEnterDate().getTime()));
        stmt.setInt(4, lease.getDuration());
        stmt.setDate(5, lease.getLeaveDate() == null ? null : new java.sql.Date(lease.getLeaveDate().getTime()));
        stmt.setString(6, lease.getPaymentOption());
        stmt.setInt(7, lease.getSecurityDeposit());
        System.out.println(stmt);
        stmt.executeUpdate();
        String query = "SELECT " + LeaseTable.LEASE_SEQUENCE + ".CURRVAL FROM DUAL";
        stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void main(String[] args) throws Exception {
        Connection conn = DBAccessor.getConnection();
        LeaseTable obj = new LeaseTable();
        Lease lease = new Lease();
        lease.setResidentId("abora");
        lease.setStatus(LeaseStatus.Pending.name());
        lease.setEnterDate(new Date());
        lease.setDuration(2);
        lease.setPaymentOption(PaymentOption.Monthly.name());

        obj.insert(conn, lease);
    }
}