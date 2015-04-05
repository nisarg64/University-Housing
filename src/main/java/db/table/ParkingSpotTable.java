package db.table;

import pojo.ParkingRequest;
import pojo.ParkingSpot;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                "availability varchar(5), "+
                "rental_fee float(6), "+
                "PRIMARY KEY (spot_id), "+
                "FOREIGN KEY (lot_id) REFERENCES PARKING_LOT(lot_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new ArrayList<String>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('CEN1','CENTENNIAL', 'Bike', 'Yes', 400)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('CEN2','CENTENNIAL', 'Small', 'Yes', 500)";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('CEN3','CENTENNIAL', 'Large', 'Yes', 600)";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('CEN4','CENTENNIAL', 'Handicap', 'Yes', 300)";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('NC1','NORTHCAMPUS', 'Small', 'Yes', 500)";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('NC2','NORTHCAMPUS', 'Large', 'Yes', 600)";
        String query7 = "INSERT INTO " + getTableName() + " VALUES('NC3','NORTHCAMPUS', 'Handicap', 'Yes', 300)";
        String query8 = "INSERT INTO " + getTableName() + " VALUES('MC1','MAINCAMPUS', 'Small', 'Yes', 500)";
        String query9 = "INSERT INTO " + getTableName() + " VALUES('MC2','MAINCAMPUS', 'Bike', 'Yes', 400)";
        String query10 = "INSERT INTO " + getTableName() + " VALUES('MC3','MAINCAMPUS', 'Large', 'Yes', 600)";
        String query11 = "INSERT INTO " + getTableName() + " VALUES('MC4','MAINCAMPUS', 'Handicap', 'Yes', 300)";


        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);
        queries.add(query11);

        DBAccessor.executeBatchQuery(conn, queries);
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

        query = "UPDATE PARKING_REQUEST SET request_status = 'renew request' " +
                "WHERE resident_id = '"+resident_id+"' AND permit_id = " +
                "(SELECT permit_id from PARKING_PERMIT " +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"')";

        System.out.println(query);
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

        query = "UPDATE PARKING_REQUEST SET request_status = 'return request' " +
                "WHERE resident_id = '"+resident_id+"' AND permit_id = " +
                "(SELECT permit_id from PARKING_PERMIT " +
                "WHERE SPOT_ID = '"+parkingSpot.getSpotId()+"')";

        try {
            executeQuery(conn,query);
        } catch (SQLException e) {
            System.err.println("Error Occurred During Parking Spot Return Request status update " + e.getMessage());
        }

        return "SUCCESS";
    }

}
