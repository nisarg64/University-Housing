package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingPermitTable implements Table {
    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE PARKINGPERMIT ("+
                "permit_id varchar(32), " +
                "spot_id varchar(32), "+
                "lot_id varchar(32), "+
                "permit_start_date timestamp, "+
                "permit_end_date timestamp, "+
                "PRIMARY KEY (permit_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKINGLOT(lot_id), "+
                "FOREIGN KEY (spot_id) REFERENCES PARKINGSPOT(spot_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    @Override
    public void dropTable(Connection conn) {

        String query = "DROP TABLE PARKINGPERMIT";
        try {
            executeQuery(conn, query);
        } catch (SQLException e) {
            System.err.println(" Table  PARKINGPERMIT : " + e.getMessage());
        }
    }
}
