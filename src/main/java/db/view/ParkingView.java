package db.view;

import pojo.ParkingLot;
import pojo.ParkingSpot;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                " PR.resident_id, PP.permit_id, PP.permit_start_date, " +
                " PP.permit_end_date, PL.lot_id, PL.lot_type," +
                " PS.spot_id, PS.spot_type, PS.availability, PS.rental_fee" +
                " FROM   PARKING_SPOT PS, " +
                " PARKING_LOT PL, " +
                " PARKING_PERMIT PP, "+
                " PARKING_REQUEST PR "+
                " WHERE (PR.permit_id = PP.permit_id AND PP.lot_id = PL.lot_id " +
                "AND PP.spot_id = PS.spot_id AND PS.lot_id = PL.lot_id) ";
        System.out.println(query);
        DBAccessor.executeQuery(conn, query);
    }

    public ParkingSpot getParkinSpot(Connection conn, String username, String spotId) {
        ParkingSpot parkingSpot = null;
        String query = "SELECT * FROM " + getViewName() + " where resident_id = '" + username + "' AND spot_id = '"+ spotId +"'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                parkingSpot = new ParkingSpot();
                parkingSpot.setSpotId(resultSet.getString("spot_id"));
                parkingSpot.setLotId(resultSet.getString("lot_id"));
                parkingSpot.setPermitId(resultSet.getInt("permit_id"));
                parkingSpot.setSpotType(resultSet.getString("spot_type"));
                parkingSpot.setAvailability(resultSet.getString("availability"));
                parkingSpot.setRentalFee(resultSet.getFloat("rental_fee"));
                parkingSpot.setPermitStartDate(resultSet.getDate("permit_start_date"));
                parkingSpot.setPermitEndDate(resultSet.getDate("permit_end_date"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Parking View " + ex.getMessage());
        }

        return parkingSpot;
    }

    public List<ParkingLot> getParkingLots(Connection conn, String username) {
        List<ParkingLot> parkingLots = new ArrayList<>();
        String query = "SELECT count(*) as vacancy, PLot,PLot_type,PType,PName, PAddress" +
                "  FROM PARKING_SPOT PS," +
                " (SELECT PL.LOT_ID as PLot, PL.LOT_TYPE as PLot_type, TYPE as PType, NAME as PName, ADDRESS as PAddress" +
                " FROM PARKING_LOT PL LEFT OUTER JOIN" +
                " (SELECT LOT_ID as Lot, NAME, TYPE, ADDRESS" +
                " from PARKING_RESIDENT_HALL_MAP PRHM, HOUSING H" +
                " WHERE PRHM.housing_id = H.housing_id)" +
                " ON PL.LOT_ID = Lot) T1 WHERE PS.LOT_ID = T1.PLot AND PS.AVAILABILITY='Yes'" +
                " GROUP BY PLot,PLot_type,PType,PName, PAddress";


        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                ParkingLot parkingLot = new ParkingLot();
                parkingLot.setLotId(resultSet.getString("PLot"));
                parkingLot.setLotType(resultSet.getString("PLot_type"));
                parkingLot.setNearbyHousing(resultSet.getString("PName"));
                parkingLot.setAddress(resultSet.getString("PAddress"));
                parkingLot.setHousingType(resultSet.getString("PType"));
                parkingLot.setVacancies(resultSet.getInt("vacancy"));
                parkingLots.add(parkingLot);
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Parking View " + ex.getMessage());
        }

        return parkingLots;
    }
}
