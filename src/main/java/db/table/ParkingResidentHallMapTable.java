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
        return "PARKING_RESEDENT_HALL_MAP";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "id varchar2(32), "+
                "lot_id varchar2(32),"+
                "hall_id varchar(32), "+
                "PRIMARY KEY (id),"+
                "FOREIGN KEY (lot_id) references PARKING_LOT(lot_id),"+
                "FOREIGN KEY (hall_id) references RESIDENT_HALL(hall_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new ArrayList<String>();

        queries.add("INSERT INTO " + getTableName() + " VALUES('1', 'CENTENNIAL', 'HID1')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('2', 'CENTENNIAL', 'HID4')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('3', 'MAINCAMPUS', 'HID2')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('4', 'MAINCAMPUS', 'HID4')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('5', 'NORTHCAMPUS', 'HID3')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('6', 'NORTHCAMPUS', 'HID1')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('7', 'NORTHCAMPUS', 'HID2')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('8', 'SOUTHCAMPUS', 'HID2')");

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
