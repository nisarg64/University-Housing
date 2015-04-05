package db.table;

import oracle.sql.NUMBER;
import pojo.ParkingRequest;
import util.DBAccessor;
import util.Utils;

import java.sql.*;
import java.util.ArrayList;
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

        String query = "CREATE TABLE " + getTableName() + " (" +
                "request_id NUMBER, " +
                "resident_id char(10), " +
                "vehicle_type varchar2(32), " +
                "isHandicapped varchar2(3), " +
                "nearby_spot_preference varchar2(3), " +
                "request_status varchar(32), " +
                "permit_id NUMBER, " +
                "PRIMARY KEY (request_id, resident_id), " +
                "FOREIGN KEY (resident_id) REFERENCES RESIDENT(res_id), " +
                "FOREIGN KEY (permit_id) REFERENCES PARKING_PERMIT(permit_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public void insertRequest(Connection conn, String resident_id, ParkingRequest parkingRequest) throws SQLException {

        String query = "INSERT INTO " + getTableName() + "(request_id,resident_id, vehicle_type, isHandicapped, " +
                "nearby_spot_preference, " + "request_status, permit_id) values(pr_sequence.NEXTVAL,'" +
                resident_id + "','" + parkingRequest.getVehicle() + "','" +
                parkingRequest.getHandicapped() + "','" + parkingRequest.getNearSpot() +
                "','pending',NULL)";
        executeQuery(conn, query);
    }

    public ParkingRequest getParkingRequest(Connection conn, String username) {

        String query = "SELECT vehicle_type, isHandicapped, nearby_spot_preference, request_status" +
                " FROM " + getTableName() + " where resident_id = '" + username + "' " +
                "AND request_id = (SELECT max(request_id) FROM " + getTableName() + " where resident_id ='" + username + "' )";

        ParkingRequest parkingRequest = null;

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while (resultSet.next()) {
                parkingRequest = new ParkingRequest();
                parkingRequest.setRequestStatus(resultSet.getString("request_status"));
                parkingRequest.setVehicle(resultSet.getString("vehicle_type"));
                parkingRequest.setHandicapped(resultSet.getString("isHandicapped"));
                parkingRequest.setNearSpot(resultSet.getString("nearby_spot_preference"));

                System.out.println(parkingRequest);

            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During Login " + ex.getMessage());
        }
        return parkingRequest;
    }

    public List<ParkingRequest> selectAll(Connection conn) {

        List<ParkingRequest> parkingRequests = new ArrayList<>();
        String query = "SELECT * from " + getTableName() +" where request_status = 'pending' " +
                "OR request_status = 'renew request' OR request_status = 'return request'  ";
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while (resultSet.next()) {
                ParkingRequest parkingRequest = new ParkingRequest();
                parkingRequest.setRequestID(String.valueOf(resultSet.getInt("request_id")));
                parkingRequest.setResidentID(resultSet.getString("resident_id"));
                parkingRequest.setVehicle(resultSet.getString("vehicle_type"));
                parkingRequest.setHandicapped(resultSet.getString("ishandicapped"));
                parkingRequest.setNearSpot(resultSet.getString("nearby_spot_preference"));
                parkingRequest.setRequestStatus(resultSet.getString("request_status"));

                parkingRequests.add(parkingRequest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return parkingRequests;
    }

    public String checkParkingAvailability(Connection conn, String requestId) throws SQLException {
        String studentRole = null;
        String studentHousing = null;
        String username = null;
        String isHandicapped = "No";
        String vehicle = null;
        int spotCount = 0;
        String spotId = null;
        String lotId = null;
        NUMBER permitId = null;
        String requestStatus = null;

        String query = "Select * from PARKING_REQUEST where request_id = "+ requestId.trim();
        System.out.println(query);

        ResultSet resultset = DBAccessor.selectQuery(conn, query);
        while(resultset.next()) {
            username = resultset.getString("resident_id");
            isHandicapped = resultset.getString("isHandicapped");
            vehicle = resultset.getString("vehicle_type");
            if(vehicle.equals("Large Cars"))
                vehicle = "Large";
            else if(vehicle.equals("Compact Cars") || vehicle.equals("Standard Cars"))
                vehicle = "Small";
            requestStatus = resultset.getString("request_status");
        }
        System.out.println(username);
        System.out.println(isHandicapped);
        System.out.println(vehicle);


        Timestamp permitEndDate = null;
        if("renew request".equals(requestStatus)){
            /*query = "SELECT permit_end_date from PARKING_PERMIT where permit_id = (SELECT permit_id from PARKING_REQUEST where request_id = "+ requestId.trim()+")";
            System.out.println(query);
            ResultSet result = DBAccessor.selectQuery(conn, query);
            while(result.next())
                permitEndDate = result.getTimestamp("permit_end_date");

            query = "UPDATE PARKING_PERMIT SET permit_end_date = "+Utils.getRenewdTimestamp(permitEndDate)+" WHERE permit_id = " +
                    "(SELECT permit_id from PARKING_REQUEST WHERE request_id = "+requestId.trim()+")";

            System.out.println(query);
            executeQuery(conn, query);*/

            query = "UPDATE PARKING_REQUEST SET request_status = 'renewed' where request_id = "+requestId.trim();
            System.out.println(query);
            executeQuery(conn,query);
            return "APPROVE";
        } else if("return request".equals(requestStatus)) {
            query = "UPDATE PARKING_SPOT SET availability = 'Yes' WHERE spot_id = " +
                    "(SELECT spot_id from PARKING_PERMIT where permit_id = " +
                    "(SELECT permit_id from PARKING_REQUEST where request_id = "+requestId.trim()+"))" ;
            executeQuery(conn,query);
            query = "UPDATE PARKING_REQUEST SET request_status = 'returned' where request_id = "+requestId.trim();
            executeQuery(conn,query);
            return "APPROVE";
        }

        query = "Select role from LOGIN where username = '" + username.trim()+"'";

        ResultSet resultset1 = DBAccessor.selectQuery(conn, query);
        while(resultset1.next()) {
            studentRole = resultset1.getString("role");
        }

        System.out.println(studentRole);
        System.out.println(studentHousing);

        // studentHousing can be private or campus Housing. Query remaining

        if ("guest".equals(studentRole) || "private".equals(studentHousing)) {
            if (isHandicapped.equals("No"))
                query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "' AND LOT_ID = (SELECT LOT_ID from PARKING_LOT where lot_type = 'General Lot')";
            else
                query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap' AND LOT_ID = (SELECT LOT_ID from PARKING_LOT where lot_type = 'General Lot')";

            System.out.println(query);
            ResultSet resultset2 = DBAccessor.selectQuery(conn, query);
            while (resultset2.next()) {
                spotCount = resultset2.getInt(1);
            }

            System.out.println(spotCount);

            if (spotCount <= 0) {
                query = "UPDATE " + getTableName() + " SET request_status = 'rejected' where request_id = " + requestId;
                System.out.println(query);
                executeQuery(conn, query);
                return "REJECT";
            } else {

                if (isHandicapped.equals("No"))
                    query = "SELECT spot_id, lot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "' AND LOT_ID = (SELECT LOT_ID from PARKING_LOT where lot_type = 'General Lot')";
                else
                    query = "SELECT spot_id, lot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap' AND LOT_ID = (SELECT LOT_ID from PARKING_LOT where lot_type = 'General Lot')";

                ResultSet resultset3 = DBAccessor.selectQuery(conn, query);
                while (resultset3.next()) {
                    spotId = resultset3.getString("spot_id");
                    lotId = resultset3.getString("lot_id");
                }

                System.out.println(spotId);
                System.out.println(lotId);

                query = "UPDATE PARKING_SPOT SET availability = 'No' where spot_id = '" + spotId + "' AND lot_id = '" + lotId + "'";
                executeQuery(conn, query);

                query = "INSERT into PARKING_PERMIT VALUES(permit_sequence.NEXTVAL, '" + spotId + "', '" + lotId + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + 150)";
                System.out.println(query);
                executeQuery(conn, query);

                query = "UPDATE " + getTableName() + " SET permit_id = permit_sequence.CURRVAL, request_status = 'approved' where request_id = " + requestId;
                System.out.println(query);
                executeQuery(conn, query);

                return "APPROVE";
            }
        } else {
            System.out.println("Campus Student");
            // Query to get student's assigned hall id
            String hallId = "HID1";
            System.out.println(hallId);
            query = "SELECT LOT_ID from parking_resident_hall_map WHERE HALL_ID = '" + hallId + "'";
            ResultSet resultSet = DBAccessor.selectQuery(conn, query);
            while (resultset.next()) {
                lotId = resultSet.getString("lot_id");
                if (isHandicapped.equals("No"))
                    query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "' AND LOT_ID = '" + lotId + "'";
                else
                    query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap' AND LOT_ID = '" + lotId + "'";

                ResultSet resultset2 = DBAccessor.selectQuery(conn, query);
                while (resultset2.next()) {
                    spotCount = resultset2.getInt(1);
                }
                System.out.println(spotCount);
                if (spotCount > 0) {
                    if (isHandicapped.equals("No"))
                        query = "SELECT spot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "' AND LOT_ID = '" + lotId + "'";
                    else
                        query = "SELECT spot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap' AND LOT_ID = '" + lotId + "'";

                    ResultSet resultset3 = DBAccessor.selectQuery(conn, query);
                    while (resultset3.next()) {
                        spotId = resultset3.getString("spot_id");
                    }
                    System.out.println(spotId);
                    System.out.println(lotId);
                    break;
                }

            }

            if (spotCount <= 0) {
                System.out.println("Not available nearby");
                if (isHandicapped.equals("No"))
                    query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "'";
                else
                    query = "SELECT count(*) from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap'";

                ResultSet resultset2 = DBAccessor.selectQuery(conn, query);
                while (resultset2.next()) {
                    spotCount = resultset2.getInt(1);
                }

                System.out.println(spotCount);

                if (spotCount <= 0) {
                    query = "UPDATE " + getTableName() + " SET request_status = 'rejected' where request_id = " + requestId;
                    System.out.println(query);
                    executeQuery(conn, query);
                    return "REJECT";
                } else {
                    if (isHandicapped.equals("No"))
                        query = "SELECT spot_id, lot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = '" + vehicle + "'";
                    else
                        query = "SELECT spot_id, lot_id from PARKING_SPOT WHERE availability = 'Yes' AND SPOT_TYPE = 'Handicap'";

                    ResultSet resultset3 = DBAccessor.selectQuery(conn, query);
                    while (resultset3.next()) {
                        spotId = resultset3.getString("spot_id");
                        lotId = resultset3.getString("lot_id");
                    }
                }

            }

            System.out.println(spotId);
            System.out.println(lotId);

            query = "UPDATE PARKING_SPOT SET availability = 'No' where spot_id = '" + spotId + "' AND lot_id = '" + lotId + "'";
            executeQuery(conn, query);

            query = "INSERT into PARKING_PERMIT VALUES(permit_sequence.NEXTVAL, '" + spotId + "', '" + lotId + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + 150)";
            System.out.println(query);
            executeQuery(conn, query);

            query = "UPDATE " + getTableName() + " SET permit_id = permit_sequence.CURRVAL, request_status = 'approved' where request_id = " + requestId;
            System.out.println(query);
            executeQuery(conn, query);

            return "APPROVE";
        }
    }
}
