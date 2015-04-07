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
public class ParkingResidentHallMapTable extends Table {

    @Override
    public String getTableName() {
        return "PARKING_RESIDENT_HALL_MAP";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "id " + ColumnTypes.ID_TYPE + " ," +
                "lot_id " + ColumnTypes.BIG_ID_TYPE + " ," +
                "housing_id "+ ColumnTypes.ID_TYPE+", "+
                "PRIMARY KEY (id),"+
                "FOREIGN KEY (lot_id) references PARKING_LOT(lot_id),"+
                "FOREIGN KEY (housing_id) references HOUSING(housing_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new ArrayList<String>();

        queries.add("INSERT INTO " + getTableName() + " VALUES('1', 'Parking Lot 1', '1')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('2', 'Parking Lot 2', '2')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('3', 'Parking Lot 3', '3')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('4', 'Parking Lot 4', '3')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('5', 'Parking Lot 5', '4')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('6', 'Parking Lot 6', '5')");

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
