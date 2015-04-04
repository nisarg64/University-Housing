package db.table;

import pojo.ParkingRequest;
import pojo.ParkingSpot;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingSpotTable extends Table {

    @Override
    public String getTableName() {
        return "PARKING_SPOT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "spot_id varchar(32), "+
                "lot_id varchar(32), "+
                "spot_type varchar(32), "+
                "availability INTEGER, " +
                "rental_fee float(6), "+
                "PRIMARY KEY (spot_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKING_LOT(lot_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public String renewSpotRequest(Connection conn, String resident_id, ParkingSpot parkingSpot)  {
        String query = "SELECT count(*) FROM PARKING_PERMIT " +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"' AND PERMIT_ID IN " +
                "(SELECT permit_id FROM PARKING_REQUEST " +
                "WHERE resident_id ='"+resident_id+"')";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                if(resultSet.getInt(1) == 0)
                    return "NULL";
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Parking Spot Renew Request " + ex.getMessage());
        }

        query = "UPDATE PARKING_REQUEST SET request_status = 'renew request'" +
                "WHERE resident_id = '"+resident_id+"' AND permit_id = " +
                "(SELECT permit_id from PARKING_PERMIT" +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"')";

        try {
            executeQuery(conn,query);
        } catch (SQLException e) {
            System.err.println("Error Occurred During Parking Spot Renew Request Update " + e.getMessage());
        }

        return "SUCCESS";
    }

    public String returnSpotRequest(Connection conn, String resident_id, ParkingSpot parkingSpot) {
        String query = "SELECT count(*) FROM PARKING_PERMIT " +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"' AND PERMIT_ID IN " +
                "(SELECT permit_id FROM PARKING_REQUEST " +
                "WHERE resident_id ='"+resident_id+"')";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                if(resultSet.getInt(1) == 0)
                    return "NULL";
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Parking Spot Return Request " + ex.getMessage());
        }

        query = "UPDATE PARKING_REQUEST SET request_status = 'return request'" +
                "WHERE resident_id = '"+resident_id+"' AND permit_id = " +
                "(SELECT permit_id from PARKING_PERMIT" +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"')";

        try {
            executeQuery(conn,query);
        } catch (SQLException e) {
            System.err.println("Error Occurred During Parking Spot Return Request status update " + e.getMessage());
        }

        return "SUCCESS";
    }

}
