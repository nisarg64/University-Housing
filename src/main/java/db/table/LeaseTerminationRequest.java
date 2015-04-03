package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Nikhil
 * Date: 28-03-15
 * Time: 08:33 PM
 */
public class LeaseTerminationRequest extends Table {

    @Override
    public String getTableName() {
        return "LEASE_TERMINATION_REQUEST";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " (" +
                "request_number integer," +
                "lease_number integer not null," +
                "leave_date date not null," +
                "status varchar2(15) not null," +
                "inspection_date date not null," +
                "PRIMARY KEY(request_number)," +
                "FOREIGN KEY(lease_number) REFERENCES LEASE" +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}