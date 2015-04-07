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
                "id varchar2(32), "+
                "lot_id varchar2(32),"+
                "housing_name varchar(32), "+
                "housing_type varchar(32),"+
                "PRIMARY KEY (id),"+
                "FOREIGN KEY (lot_id) references PARKING_LOT(lot_id),"+
                "FOREIGN KEY (housing_name) references HOUSING(name),"+
                "FOREIGN KEY (housing_type) references HOUSING(type))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new ArrayList<String>();

        queries.add("INSERT INTO " + getTableName() + " VALUES('1', 'Parking Lot 1', 'Residence Halls', 'Gryffindor Hall')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('2', 'Parking Lot 2', 'Residence Halls', 'Slytherin Hall')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('3', 'Parking Lot 3', 'General Student Apartments', 'Ravenclaw Hall')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('4', 'Parking Lot 4', 'General Student Apartments', Ravenclaw')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('5', 'Parking Lot 5', 'General Student Apartments', 'Hufflepuff')");
        queries.add("INSERT INTO " + getTableName() + " VALUES('1', 'Parking Lot 6', 'Family Apartments', 'Hogwarts')");

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
