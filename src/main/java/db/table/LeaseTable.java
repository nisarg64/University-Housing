package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 28-03-15
 */
public class LeaseTable extends Table {

    // Table Name
    public static final String LEASE_TABLE = "LEASE";

    // Column Names
    public static final String LEASE_NUMBER = "lease_number";
    public static final String RES_ID = "res_id";
    public static final String STATUS = "status";
    public static final String ENTER_DATE = "enter_date";
    public static final String DURATION = "duration";
    public static final String LEAVE_DATE = "leave_date";
    public static final String PAYMMENT_OPTION = "paymment_option";
    public static final String SECURITY_DEPOSIT = "security_deposit";
    public static final String CUTOFF_DATE = "cutoff_date";

    @Override
    public String getTableName() {
        return LEASE_TABLE;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " (" +
                LEASE_NUMBER + " integer," +
                RES_ID + " char(10) not null," +
                STATUS + " varchar2(15) not null," +
                ENTER_DATE + " date not null," +
                // TODO academic year
                DURATION + " integer not null," +
                LEAVE_DATE + " date," +
                PAYMMENT_OPTION + " varchar2(10) not null," +
                SECURITY_DEPOSIT + " number not null," +
                CUTOFF_DATE + " date," +
                "PRIMARY KEY(" + LEASE_NUMBER + ")," +
                "FOREIGN KEY(" + RES_ID + ") REFERENCES RESIDENT" +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES(1, 'akagrawa', 'Pending'," +
                "sysdate, 2, null, 'Monthly', 500, sysdate + 30)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES(2, 'abora', 'Processed'," +
                "sysdate, 3, null, 'Monthly', 1000, sysdate + 90)";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}
