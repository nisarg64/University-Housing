package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : anand
 * Created on 3/27/15.
 */
public class ResidentHallTable extends Table{

    @Override
    public String getTableName() {
        return "RESIDENT_HALL";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE RESIDENT_HALL (" +
                " hall_id VARCHAR(20), " +
                " name VARCHAR(32), " +
                " address VARCHAR(100), " +
                " manager VARCHAR(32), " +
                " tel_number VARCHAR(12), " +
                " student_category VARCHAR(6), " +
                " PRIMARY KEY (hall_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO RESIDENT_HALL VALUES('HID1', 'Hall 1', 'Cool drive road', 'Mr. Perfect', '919-900-1000', 'UG1')";
        String query2 = "INSERT INTO RESIDENT_HALL VALUES('HID2', 'Hall 2', 'Hill road', 'Mr. Hogan', '919-900-2000', 'UG2')";
        String query3 = "INSERT INTO RESIDENT_HALL VALUES('HID3', 'Hall 3', 'Park road', 'Mr. Cena', '919-900-3000', 'UG3')";
        String query4 = "INSERT INTO RESIDENT_HALL VALUES('HID4', 'Hall 4', 'Pullen drive', 'Mr. Rock', '919-900-4000', 'UG4')";
        String query5 = "INSERT INTO RESIDENT_HALL VALUES('HID5', 'Hall 5', 'MG road', 'Mr. Khali', '919-900-5000', 'GDS')";
        String query6 = "INSERT INTO RESIDENT_HALL VALUES('HID6', 'Hall 6', 'Avent ferry road', 'Mr. Brock', '919-900-6000', 'PHD')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE RESIDENT_HALL";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  RESIDENT_HALL : " + ex.getMessage());
        }
    }
}
