package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
                "lot_id " + ColumnTypes.BIG_ID_TYPE + ", " +
                "lot_type " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + ", " +
                "PRIMARY KEY (lot_id)) ";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new ArrayList<>();

        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 1', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 2', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 3', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 4', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 5', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 6', 'Campus Lot')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('Parking Lot 7', 'General Lot')");

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
