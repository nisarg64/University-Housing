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
public class RoomTable extends Table {

    @Override
    public String getTableName() {
        return "ROOM";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() +" (" +
                " place_num VARCHAR(20), " +
                " room_num VARCHAR(20), " +
                " monthly_rent INTEGER, " +
                " PRIMARY KEY (place_num) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        // Resident hall rooms:
        // rooms in hall 1
        String query1 = "INSERT INTO " + getTableName() +" VALUES('HID1_O1', 'RNO1', 400)";
        String query2 = "INSERT INTO " + getTableName() +" VALUES('HID1_O2', 'RNO2', 400)";
        String query3 = "INSERT INTO " + getTableName() +" VALUES('HID1_O3', 'RNO3', 400)";
        String query4 = "INSERT INTO " + getTableName() +" VALUES('HID1_O4', 'RNO4', 400)";
        String query5 = "INSERT INTO " + getTableName() +" VALUES('HID1_O5', 'RNO5', 400)";

        String query6 = "INSERT INTO " + getTableName() +" VALUES('HID2_02', 'RNO2', 500)";
        String query7 = "INSERT INTO " + getTableName() +" VALUES('HID3_03', 'RNO3', 300)";
        String query8 = "INSERT INTO " + getTableName() +" VALUES('HID4_04', 'RNO4', 400)";
        String query9 = "INSERT INTO " + getTableName() +" VALUES('HID5_05', 'RNO5', 500)";
        String query10 = "INSERT INTO " + getTableName() +" VALUES('HID6_06', 'RNO6', 400)";

        // General apartment " + getTableName() +"s:
        String query11 = "INSERT INTO " + getTableName() +" VALUES('G1_O1', 'RNO1', 400)";
        String query12 = "INSERT INTO " + getTableName() +" VALUES('G1_O2', 'RNO2', 400)";
        String query13 = "INSERT INTO " + getTableName() +" VALUES('G1_O3', 'RNO3', 400)";
        String query14 = "INSERT INTO " + getTableName() +" VALUES('G1_O4', 'RNO4', 400)";
        String query15 = "INSERT INTO " + getTableName() +" VALUES('G1_O5', 'RNO5', 400)";

        String query16 = "INSERT INTO " + getTableName() +" VALUES('G2_02', 'RNO2', 500)";
        String query17 = "INSERT INTO " + getTableName() +" VALUES('G3_03', 'RNO3', 300)";
        String query18 = "INSERT INTO " + getTableName() +" VALUES('G4_04', 'RNO4', 400)";
        String query19 = "INSERT INTO " + getTableName() +" VALUES('G5_05', 'RNO5', 500)";
        String query20 = "INSERT INTO " + getTableName() +" VALUES('G6_06', 'RNO6', 400)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);
        queries.add(query11);
        queries.add(query12);
        queries.add(query13);
        queries.add(query14);
        queries.add(query15);
        queries.add(query16);
        queries.add(query17);
        queries.add(query18);
        queries.add(query19);
        queries.add(query20);

        DBAccessor.executeBatchQuery(conn, queries);
    }


}
