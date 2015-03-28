package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/27/15.
 */
public class StudentTable implements Table{

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE STUDENT (" +
                " student_id CHAR(10), " +
                " category VARCHAR(6), " +
                " status VARCHAR(10), " +
                " course VARCHAR(32), " +
                " PRIMARY KEY (student_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    @Override
    public void dropTable(Connection conn) {
        try {
            String query = "DROP TABLE STUDENT";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  STUDENT : " + ex.getMessage());
        }
    }
}
