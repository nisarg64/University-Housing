package db.table;

import pojo.ParkingRequest;
import pojo.Resident;
import pojo.Student;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingRequestTable extends Table {

    @Override
    public String getTableName() {
        return "PARKING_REQUEST";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {


        String query = "CREATE TABLE " + getTableName() + " ("+
                "request_id NUMBER, "+
                "resident_id char(10), "+
                "vehicle_type varchar2(32), "+
                "isHandicapped varchar2(3), "+
                "nearby_spot_preference varchar2(3), "+
                "request_status varchar(32), "+
                "permit_id varchar(32), " +
                "PRIMARY KEY (request_id, resident_id), "+
                "FOREIGN KEY (resident_id) REFERENCES RESIDENT(res_id), "+
                "FOREIGN KEY (permit_id) REFERENCES PARKING_PERMIT(permit_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public void insertRequest(Connection conn, String resident_id, ParkingRequest parkingRequest) throws SQLException {

        String query = "INSERT INTO "+ getTableName() + "(request_id,resident_id, vehicle_type, isHandicapped, " +
                "nearby_spot_preference, " + "request_status, permit_id) values(pr_sequence.NEXTVAL,'"+
                resident_id +"','"+ parkingRequest.getVehicle()+"','"+
                parkingRequest.getHandicapped()+"','"+ parkingRequest.getNearSpot()+
                "','pending',NULL)";
        executeQuery(conn,query);
    }

    public ParkingRequest getParkingRequest(Connection conn, String username) {

        String query = "SELECT vehicle_type, isHandicapped, nearby_spot_preference, request_status" +
                " FROM "+ getTableName()+" where resident_id = '"+username+"' " +
                "AND request_id = (SELECT max(request_id) FROM "+getTableName()+" where resident_id ='"+username+"' )";

        ParkingRequest parkingRequest = null;

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                parkingRequest = new ParkingRequest();
                parkingRequest.setRequestStatus(resultSet.getString("request_status"));
                parkingRequest.setVehicle(resultSet.getString("vehicle_type"));
                parkingRequest.setHandicapped(resultSet.getString("isHandicapped"));
                parkingRequest.setNearSpot(resultSet.getString("nearby_spot_preference"));

                System.out.println(parkingRequest);

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Login " + ex.getMessage());
        }
        return parkingRequest;
    }

    public List<ParkingRequest> selectAll(Connection conn) {

        List<ParkingRequest> parkingRequests = new ArrayList<>() ;
        String query = "SELECT * from " + getTableName();
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                ParkingRequest parkingRequest = new ParkingRequest();
                parkingRequest.setRequestID(String.valueOf(resultSet.getInt("request_id")));
                parkingRequest.setResidentID(resultSet.getString("resident_id"));
                parkingRequest.setVehicle(resultSet.getString("vehicle_type"));
                parkingRequest.setVehicle(resultSet.getString("ishandicapped"));
                parkingRequest.setVehicle(resultSet.getString("nearby_spot_preference"));
                parkingRequest.setVehicle(resultSet.getString("request_status"));

                parkingRequests.add(parkingRequest);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return parkingRequests;
    }
}
