package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingLotTable implements Table {
    @Override
    public void createTable(Connection conn) throws SQLException {
        String query =  "CREATE TABLE PARKINGLOT ("+
                "lot_id varchar(32), "+
                "lot_type varchar(32), "+
                "nearby_housing_id varchar(32), "+
                "PRIMARY KEY (lot_id), "+
                "FOREIGN KEY (nearby_housing_id) REFERENCES HOUSING_OPTIONS(housing_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    @Override
    public void dropTable(Connection conn) {

        String query = "DROP TABLE PARKINGLOT";
        try {
            executeQuery(conn, query);
        } catch (SQLException e) {
            System.err.println(" Table  PARKINGLOT : " + e.getMessage());
        }
    }
}
