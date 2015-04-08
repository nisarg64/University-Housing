package db.table;

import pojo.LeaseRequest;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 08-04-15
 */
public class LeaseRequestTable extends Table {

    // Table Name
    public static final String TABLE_NAME = "LEASE_REQUEST";
    public static final String LEASE_REQUEST_SEQUENCE = "lease_request_sequence";

    // Column Names
    public static final String REQUEST_NUMBER = "request_number";
    public static final String RES_ID = "res_id";
    public static final String STATUS = "status";
    public static final String ENTER_DATE = "enter_date";
    public static final String DURATION = "duration";
    public static final String PAYMENT_OPTION = "payment_option";
    public static final String USE_PRIVATE_ACCOMMODATION = "use_private_accommodation";
    public static final String UPDATED_BY = "updated_by";
    public static final String UPDATED_ON = "updated_on";
    public static final String PRIMARY_KEY_CONSTRAINT = "PRIMARY KEY";
    public static final String FOREIGN_KEY_CONSTRAINT = "FOREIGN KEY";
    public static final String REFERENCES_STR = "REFERENCES";

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
                USE_PRIVATE_ACCOMMODATION + " " + ColumnTypes.BOOLEAN_TYPE + " default '0' not null," +
                UPDATED_BY + " " + ColumnTypes.ID_TYPE + "," +
                UPDATED_ON + " " + ColumnTypes.DATE_TYPE + " ," +
                PRIMARY_KEY_CONSTRAINT + "(" + REQUEST_NUMBER + ")," +
                FOREIGN_KEY_CONSTRAINT + "(" + RES_ID + ") " + REFERENCES_STR + " " + "RESIDENT" + "," +
                FOREIGN_KEY_CONSTRAINT + "(" + UPDATED_BY + ") " + REFERENCES_STR + " " + "STAFF" +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();

        // Sequence: LEASE_NUMBER, RES_ID, STATUS, ENTER_DATE, DURATION, PAYMENT_OPTION,
        // USE_PRIVATE_ACCOMMODATION, UPDATED_BY, UPDATED_ON

        /*queries.add(createInsertQuery(TABLE_NAME, "1", "'100540001'", "'" + LeaseTable.RequestStatus.Completed + "'", "to_date('01-JAN-2014', 'dd-MON-yyyy')",
                "2", "'" + LeaseTable.PaymentOption.Semester+ "'", "500", "'0'", "001", "'1'"));*/

        //DBAccessor.executeBatchQuery(conn, queries);
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

    public int insert(Connection conn, LeaseRequest leaseRequest) throws SQLException {
        String query = "Select " + LEASE_REQUEST_SEQUENCE + ".NEXTVAL FROM DUAL";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int requestNumber = rs.getInt(1);

        String sql = createInsertPreparedStatement(TABLE_NAME, 0, REQUEST_NUMBER, RES_ID, STATUS, ENTER_DATE,
                DURATION, PAYMENT_OPTION, USE_PRIVATE_ACCOMMODATION);
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, requestNumber);
        stmt.setString(2, leaseRequest.getResidentId());
        stmt.setString(3, LeaseTable.RequestStatus.InProgress.name());
        stmt.setDate(4, new java.sql.Date(leaseRequest.getEnterDate().getTime()));
        stmt.setInt(5, leaseRequest.getDuration());
        stmt.setString(6, leaseRequest.getPaymentOption());
        stmt.setBoolean(7, leaseRequest.isUsePrivateAccommodation());
        stmt.executeUpdate();
        System.out.println(stmt);
        return requestNumber;
    }

    public void updateStatus(Connection conn, int requestNumber, LeaseTable.RequestStatus status) throws SQLException {
        String sql = "update " + TABLE_NAME + " set " + STATUS + " = '" + status +
                "' where " + REQUEST_NUMBER + " = " + requestNumber;
        DBAccessor.executeQuery(conn, sql);
    }
}
