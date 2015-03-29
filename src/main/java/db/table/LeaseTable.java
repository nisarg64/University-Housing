package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Nikhil
 * Date: 28-03-15
 */
public class LeaseTable extends Table {

    @Override
    public String getTableName() {
        return "Lease";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " (" +
                "lease_number integer," +
                "res_id char(10) not null," +
                "status varchar2(15) not null," +
                "duration integer not null," +
                "enter_date date not null," +
                "leave_date date," +
                "cutoff_date date," +
                "paymment_option varchar2(10) not null," +
                "security_deposit number not null," +
                "PRIMARY KEY(lease_number)," +
                "FOREIGN KEY(res_id) REFERENCES RESIDENT" +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
