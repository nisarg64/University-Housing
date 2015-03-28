package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingSpotTable extends Table {

    @Override
    public String getTableName() {
        return "PARKINGSPOT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "spot_id varchar(32), "+
                "lot_id varchar(32), "+
                "spot_type varchar(32), "+
                "availability char(1), " +
                "rental_fee float(6), "+
                "PRIMARY KEY (spot_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKINGLOT(lot_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
