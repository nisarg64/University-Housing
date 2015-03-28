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
public class LoginTable implements Table{

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE LOGIN (" +
                " username VARCHAR(32), " +
                " password VARCHAR(32), " +
                " role VARCHAR(10) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<String>();
        String query1 = "INSERT INTO LOGIN VALUES('akagrawa', 'abc123', 'student')";
        String query2 = "INSERT INTO LOGIN VALUES('abora', 'abc123', 'student')";
        String query3 = "INSERT INTO LOGIN VALUES('kogan', 'xyz123', 'staff')";
        String query4 = "INSERT INTO LOGIN VALUES('nkgandh', 'xyz123', 'guest')";
        String query5 = "INSERT INTO LOGIN VALUES('nkdalmia', 'abc123', 'guest')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE LOGIN";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  LOGIN : " + ex.getMessage());
        }
    }
}
