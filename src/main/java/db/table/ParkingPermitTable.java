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
                "permit_id " + ColumnTypes.NUMBER_TYPE + " ," +
                "spot_id " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                "lot_id " + ColumnTypes.BIG_ID_TYPE + " ," +
                "permit_start_date " + ColumnTypes.TIMESTAMP + " ," +
                "permit_end_date " + ColumnTypes.TIMESTAMP + " ," +
                "PRIMARY KEY (permit_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKING_LOT(lot_id), "+
                "FOREIGN KEY (spot_id) REFERENCES PARKING_SPOT(spot_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
