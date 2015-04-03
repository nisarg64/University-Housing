package db.view;

import pojo.ParkingLot;
import pojo.ParkingSpot;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nisarg on 4/3/15.
 */
public class ParkingView extends View{

    @Override
    public String getViewName() {
        return "PARKING_SPOT_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT  " +
                " PR.resident_id, PP.permit_id, PL.lot_id, PL.lot_type, PL.nearby_housing_id" +
                " PS.spot_id, PS.spot_type, PS.availability, PS.rental_fee" +
                " FROM   PARKING_SPOT PS " +
                " join " +
                " PARKING_LOT PL " +
                " join "+
                " PARKING_PERMIT PP "+
                " join "+
                " PARKING_REQUEST PR "+
                " on (PR.permit_id = PP.permit_id AND PP.lot_id = PL.lot_id " +
                "AND PP.spot_id = PS.spot_id AND PS.lot_id = PL.lot_id) ";
        DBAccessor.executeQuery(conn, query);
    }

    public ParkingSpot getParkinSpot(Connection conn, String username) {
        ParkingSpot parkingSpot = null;
        String query = "SELECT * FROM " + getViewName() + " where resident_id = '" + username + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                parkingSpot = new ParkingSpot();
                parkingSpot.setSpotId(resultSet.getString("spot_id"));
                parkingSpot.setLotId(resultSet.getString("lot_id"));
                parkingSpot.setPermit_id(resultSet.getString("permit_id"));
                parkingSpot.setSpotType(resultSet.getString("spot_type"));
                parkingSpot.setAvailability(resultSet.getInt("availability"));
                parkingSpot.setRentalFee(resultSet.getFloat("rental_fee"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Student Profile View " + ex.getMessage());
        }

        return parkingSpot;
    }

    public ParkingLot getParkinLot(Connection conn, String username) {
        ParkingLot parkingLot = null;
        String query = "SELECT * FROM " + getViewName() + " where resident_id = '" + username + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                parkingLot = new ParkingLot();
                parkingLot.setLotId(resultSet.getString("lot_id"));
                parkingLot.setPermitId(resultSet.getString("permit_id"));
                parkingLot.setLotType(resultSet.getString("lot_type"));
                parkingLot.setNearbyHousing(resultSet.getString("nearby_housing_id"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Student Profile View " + ex.getMessage());
        }

        return parkingLot;
    }
}
