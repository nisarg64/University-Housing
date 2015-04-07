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

        queries.add("INSERT INTO " + getTableName() + " VALUES('001','Parking Lot 1', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('002','Parking Lot 1', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('003','Parking Lot 1', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('004','Parking Lot 1', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('005','Parking Lot 2', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('006','Parking Lot 2', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('007','Parking Lot 2', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('008','Parking Lot 2', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('009','Parking Lot 2', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('010','Parking Lot 2', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('011','Parking Lot 3', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('012','Parking Lot 3', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('013','Parking Lot 3', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('014','Parking Lot 3', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('015','Parking Lot 3', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('016','Parking Lot 3', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('017','Parking Lot 4', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('018','Parking Lot 4', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('019','Parking Lot 4', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('020','Parking Lot 4', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('021','Parking Lot 5', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('022','Parking Lot 5', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('023','Parking Lot 5', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('024','Parking Lot 5', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('025','Parking Lot 5', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('026','Parking Lot 5', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('027','Parking Lot 5', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('028','Parking Lot 5', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('029','Parking Lot 6', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('030','Parking Lot 6', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('031','Parking Lot 6', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('032','Parking Lot 6', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('033','Parking Lot 6', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('034','Parking Lot 6', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('035','Parking Lot 6', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('036','Parking Lot 6', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('037','Parking Lot 6', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('038','Parking Lot 6', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('039','Parking Lot 6', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('040','Parking Lot 6', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('041','Parking Lot 7', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('042','Parking Lot 7', 'Handicap', 'Yes', 25)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('043','Parking Lot 7', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('044','Parking Lot 7', 'Bike', 'Yes', 30)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('045','Parking Lot 7', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('046','Parking Lot 7', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('047','Parking Lot 7', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('048','Parking Lot 7', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('049','Parking Lot 7', 'Small', 'Yes', 35)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('050','Parking Lot 7', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('051','Parking Lot 7', 'Large', 'Yes', 40)");
        queries.add("INSERT INTO " + getTableName() + " VALUES('052','Parking Lot 7', 'Large', 'Yes', 40)");


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
