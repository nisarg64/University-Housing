package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO GUEST VALUES('approval1','pending')";
        String query2 = "INSERT INTO GUEST VALUES('approval2','approved')";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
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
