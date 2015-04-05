package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingLotTable extends Table {

    @Override
    public String getTableName() {
        return "PARKING_LOT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query =  "CREATE TABLE " + getTableName() + " ("+
                "lot_id varchar(32), "+
                "lot_type varchar(32), "+
                "PRIMARY KEY (lot_id)) ";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new ArrayList<String>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('CENTENNIAL', 'General Lot')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('NORTHCAMPUS', 'Campus Lot')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('MAINCAMPUS', 'Campus Lot')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('SOUTHCAMPUS', 'Campus Lot')";


        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
