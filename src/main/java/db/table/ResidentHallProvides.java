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
public class ResidentHallProvides extends Table {

    @Override
    public String getTableName() {
        return "RESIDENT_HALL_PROVIDES";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " hall_id VARCHAR(20), " +
                " place_num VARCHAR(20), " +
                " PRIMARY KEY (hall_id, place_num), " +
                " FOREIGN KEY (hall_id) REFERENCES RESIDENT_HALL, " +
                " FOREIGN KEY (place_num) REFERENCES ROOM " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('HID1', 'HID1_O1')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('HID1', 'HID1_O2')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('HID1', 'HID1_O3')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('HID1', 'HID1_O4')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('HID1', 'HID1_O5')";

        String query6 = "INSERT INTO " + getTableName() + " VALUES('HID2', 'HID2_02')";
        String query7 = "INSERT INTO " + getTableName() + " VALUES('HID3', 'HID3_03')";
        String query8 = "INSERT INTO " + getTableName() + " VALUES('HID4', 'HID4_04')";
        String query9 = "INSERT INTO " + getTableName() + " VALUES('HID5', 'HID5_05')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE RESIDENT_HALL_PROVIDES";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  RESIDENT_HALL_PROVIDES : " + ex.getMessage());
        }
    }
}
