package db.table;

import pojo.LeaseTerminationRequest;
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
public class LeaseTerminationRequestTable extends Table {

    public static final String TABLE_NAME = "LEASE_TERMINATION_REQUEST";

    public static final String REQUEST_NUMBER = "request_number";
    public static final String LEASE_REQUEST_NUMBER = "lease_request_number";
    public static final String LEAVE_DATE = "leave_date";
    public static final String STATUS = "status";
    public static final String INSPECTION_DATE = "inspection_date";
    public static final String REASON = "reason";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + "," +
                LEASE_REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + " not null," +
                LEAVE_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                STATUS + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                INSPECTION_DATE + " " + ColumnTypes.DATE_TYPE + " ," +
                REASON + " " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " not null," +
                "PRIMARY KEY(" + REQUEST_NUMBER + ")," +
                LeaseTable.FOREIGN_KEY_CONSTRAINT + "(" + LEASE_REQUEST_NUMBER + ") " + LeaseTable.REFERENCES_STR + " " + LeaseTable.TABLE_NAME +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    public void insert(Connection conn, LeaseTerminationRequest request) throws SQLException {
        String sql = createInsertPreparedStatement(TABLE_NAME, 0, REQUEST_NUMBER, LEASE_REQUEST_NUMBER, LEAVE_DATE, STATUS, REASON);
        sql = sql.replaceFirst("\\?", LeaseTable.REQUEST_SEQUENCE + ".nextval");
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, request.getLease().getLeaseNumber());
        stmt.setDate(2, new java.sql.Date(request.getLeaveDate().getTime()));
        stmt.setString(3, LeaseTable.RequestStatus.Pending.name());
        stmt.setString(4, request.getReason());
        System.out.println(stmt);
        stmt.executeUpdate();
    }

    public void updateStatus(Connection conn, LeaseTerminationRequest request, LeaseTable.RequestStatus status) throws SQLException {
        String sql = "update " + TABLE_NAME + " set " + STATUS + " = '" + status +
                "' where " + REQUEST_NUMBER + " = " + request.getRequestNumber();
        DBAccessor.executeQuery(conn, sql);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        queries.add(createInsertQuery(TABLE_NAME, "3", "'1'", "to_date('31-Jul-2014', 'dd-MON-yyyy')", "'" + LeaseTable.RequestStatus.Completed.name() + "'", "to_date('30-Jul-2014', 'dd-MON-yyyy')", "'Leaving College'"));
        DBAccessor.executeBatchQuery(conn, queries);
    }
}
