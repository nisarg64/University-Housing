package db.table;

import pojo.ParkingSpot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.DBAccessor.executeBatchQuery;
import static util.DBAccessor.executeQuery;
import static util.DBAccessor.selectQuery;

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
                "permit_start_date " + ColumnTypes.DATE_TYPE + " ," +
                "permit_end_date " + ColumnTypes.DATE_TYPE + " ," +
                "PRIMARY KEY (permit_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKING_LOT(lot_id), "+
                "FOREIGN KEY (spot_id) REFERENCES PARKING_SPOT(spot_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {


        //String query = "Update PARKING_PERMIT SET request_status = 'approved' AND permit_id = ;
    }

    public List<ParkingSpot> getResidentParkingSpots(Connection conn, String username) {
        List<ParkingSpot> allSpots = new ArrayList<ParkingSpot>();;

        String query = "SELECT * from PARKING_PERMIT where permit_id IN " +
                "(SELECT permit_id from PARKING_REQUEST where resident_id = '"+username+"' " +
                "AND request_status <> 'returned' )";
        System.out.println(query);
        try {
            ResultSet resultSet = selectQuery(conn, query);
            while(resultSet.next()){
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotId(resultSet.getString("spot_id"));
                parkingSpot.setLotId(resultSet.getString("lot_id"));
                parkingSpot.setPermitId(resultSet.getInt("permit_id"));
                allSpots.add(parkingSpot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSpots;
    }
}
