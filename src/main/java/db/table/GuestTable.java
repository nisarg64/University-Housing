package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/27/15.
 */
public class GuestTable implements Table{

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE GUEST(" +
                " approval_id CHAR(10), " +
                " status VARCHAR(10), " +
                " PRIMARY KEY (approval_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    @Override
    public void dropTable(Connection conn) {
        try {
            String query = "DROP TABLE GUEST";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  GUEST : " + ex.getMessage());
        }
    }
}
