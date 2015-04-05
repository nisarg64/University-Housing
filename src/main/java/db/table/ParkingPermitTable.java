package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingPermitTable extends Table {

    @Override
    public String getTableName() {
        return "PARKING_PERMIT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "permit_id NUMBER, " +
                "spot_id varchar(32), "+
                "lot_id varchar(32), "+
                "permit_start_date timestamp, "+
                "permit_end_date timestamp, "+
                "PRIMARY KEY (permit_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKING_LOT(lot_id), "+
                "FOREIGN KEY (spot_id) REFERENCES PARKING_SPOT(spot_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
