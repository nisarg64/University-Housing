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
    public static final String LEASE_TERMINATION_REQUEST_SEQUENCE = "ltr_seq";

    public static final String REQUEST_NUMBER = "request_number";
    public static final String LEAVE_DATE = "leave_date";
    public static final String STATUS = "status";
    public static final String INSPECTION_DATE = "inspection_date";
    public static final String REASON = "reason";

    public enum LeaseTerminationRequestStatus{
            Pending, Processed, Completed, Cancelled;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                REQUEST_NUMBER + " integer," +
                LeaseTable.LEASE_NUMBER + " " + ColumnTypes.INTEGER_TYPE + " not null," +
                LEAVE_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                STATUS + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                INSPECTION_DATE + " " + ColumnTypes.DATE_TYPE + " ," +
                REASON + " " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " not null," +
                "PRIMARY KEY(" + REQUEST_NUMBER + ")," +
                "FOREIGN KEY(lease_number) REFERENCES " + LeaseTable.TABLE_NAME +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    public void insert(Connection conn, LeaseTerminationRequest request) throws SQLException {
        String sql = createInsertSql(TABLE_NAME, 0, REQUEST_NUMBER, LeaseTable.LEASE_NUMBER, LEAVE_DATE, STATUS, REASON);
        sql = sql.replaceFirst("\\?", LEASE_TERMINATION_REQUEST_SEQUENCE + ".nextval");
        System.out.println();
        System.out.println(sql);
        System.out.println();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, request.getLease().getLeaseNumber());
        stmt.setDate(2, new java.sql.Date(request.getLeaveDate().getTime()));
        stmt.setString(3, LeaseTerminationRequestStatus.Pending.name());
        stmt.setString(4, request.getReason());
        System.out.println(stmt);
        stmt.executeUpdate();
    }

    public static void main(String[] args) {
        Table table = new LeaseTerminationRequestTable();
        String sql = table.createInsertSql(TABLE_NAME, 0, REQUEST_NUMBER, LeaseTable.LEASE_NUMBER, LEAVE_DATE, STATUS, REASON);
        sql = sql.replaceFirst("\\?", LEASE_TERMINATION_REQUEST_SEQUENCE + ".nextval");
        System.out.println();
        System.out.println(sql);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES(1, 1, sysdate + 15," +
                "'Pending', sysdate + 13, 'Leaving College')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES(2, 2, sysdate + 10," +
                "'Processed', sysdate + 13, 'Unknown')";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}
