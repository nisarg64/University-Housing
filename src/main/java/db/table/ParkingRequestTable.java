package db.table;

import db.view.LeaseView;
import pojo.Lease;
import pojo.ParkingRequest;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.DBAccessor.*;

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
                "request_id " + ColumnTypes.NUMBER_TYPE + " ," +
                "resident_id " + ColumnTypes.ID_TYPE + " ," +
                "vehicle_type " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                "isHandicapped " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                "nearby_spot_preference " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                "request_status " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                "permit_id " + ColumnTypes.NUMBER_TYPE + " ," +
                "updated_by "+ColumnTypes.ID_TYPE+ " DEFAULT NULL, "+
                "updated_on "+ColumnTypes.DATE_TYPE +" DEFAULT NULL, "+
                "PRIMARY KEY (request_id), " +
                "FOREIGN KEY (resident_id) REFERENCES RESIDENT(res_id), " +
                "FOREIGN KEY (updated_by) REFERENCES STAFF(STAFF_NUM), " +
                "FOREIGN KEY (permit_id) REFERENCES PARKING_PERMIT(permit_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new ArrayList<>();

        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '003', 'Parking Lot 1', '01-Jan-2014','31-Jul-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540001', 'Small' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '013', 'Parking Lot 3', '01-Jan-2014','31-Jul-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540002', 'Small' , 'No', 'No', 'approved', permit_sequence.currval , null, null)");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '002', 'Parking Lot 1', '01-Jan-2014','31-May-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540003', 'Bike' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540004', 'Small' , 'No', 'No', 'pending', null, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '027', 'Parking Lot 5', '01-Jan-2014','31-May-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540005', 'Large' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '025', 'Parking Lot 5', '01-Jan-2014','31-Jul-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540006', 'Small' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '007', 'Parking Lot 2', '01-Jan-2014','31-Jul-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540007', 'Small' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '045', 'Parking Lot 7', '01-Mar-2014','30-Apr-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '200540001', 'Small' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '200540002', 'Small' , 'No', 'No', 'pending', null, null, null )");
        queries.add("INSERT into PARKING_PERMIT VALUES(permit_sequence.nextval, '038', 'Parking Lot 6', '01-Jan-2014','31-Jul-2014')");
        queries.add("INSERT into "+getTableName()+" VALUES(pr_sequence.nextval, '100540008', 'Large' , 'No', 'No', 'approved', permit_sequence.currval, null, null )");



        System.out.println(queries.get(0));
        executeBatchQuery(conn, queries);

        String query2 = "Update PARKING_SPOT SET availability = 'No' " +
                "WHERE spot_id IN (SELECT spot_id from PARKING_PERMIT)";
        executeQuery(conn,query2);
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

    public String checkParkingAvailability(Connection conn, String requestId, String operator) throws Exception {
        String studentRole = null;
        String studentHousing = null;
        String username = null;
        String isHandicapped = "No";
        String vehicle = null;
        int spotCount = 0;
        String spotId = null;
        String lotId = null;
        Integer permitId = null;
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
            permitId = resultset.getInt("permit_id");
        }
        System.out.println(username);
        System.out.println(isHandicapped);
        System.out.println(vehicle);



        if("renew request".equals(requestStatus)){

           query = "UPDATE PARKING_PERMIT SET permit_end_date = " +
                    "(SELECT permit_end_date + 150 from PARKING_PERMIT "+
                    " where permit_id = " +permitId+")" +
                    " WHERE permit_id = " + permitId ;

            System.out.println(query);
            executeQuery(conn, query);

            System.out.println("here");
            query = "UPDATE PARKING_REQUEST SET request_status = 'renewed', updated_by = "+operator+", updated_on = SYSDATE  where request_id = "+requestId.trim()+" AND resident_id = '"+username.trim()+"'";
            System.out.println(query);
            executeQuery(conn,query);
            return "APPROVE";
        } else if("return request".equals(requestStatus)) {
            query = "UPDATE PARKING_PERMIT SET permit_end_date = SYSDATE" +
                    " WHERE permit_id = " + permitId ;

            System.out.println(query);
            executeQuery(conn, query);

            query = "UPDATE PARKING_SPOT SET availability = 'Yes' WHERE spot_id = " +
                    "(SELECT spot_id from PARKING_PERMIT where permit_id = " +
                    "(SELECT permit_id from PARKING_REQUEST where request_id = "+requestId.trim()+"))" ;
            executeQuery(conn,query);
            query = "UPDATE PARKING_REQUEST SET request_status = 'returned', updated_by = "+operator+", updated_on = SYSDATE  where request_id = "+requestId.trim()+" AND resident_id = '"+username.trim()+"'";
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

        Lease lease = new Lease();
        LeaseView leaseView = new LeaseView();
        lease = leaseView.viewCurrentLease(conn, username);
        if(lease != null){
            if(lease.isUsePrivateAccommodation())
                studentHousing = "private";
            else
                studentHousing = "campus";
        }

        query = "SELECT count(*) from PARKING_REQUEST where resident_id = '"+username.trim()+"'";
        ResultSet resultSet7 = selectQuery(conn,query);
        int requestCount = 0;
        while(resultSet7.next()){
            requestCount = resultSet7.getInt(1);
        }
        if ("guest".equals(studentRole) || "private".equals(studentHousing) || requestCount > 1) {
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
                query = "UPDATE " + getTableName() + " SET request_status = 'rejected', updated_by = "+operator+", updated_on = SYSDATE  where request_id = " + requestId+" AND resident_id = '"+username.trim()+"'";
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

                query = "INSERT into PARKING_PERMIT VALUES(permit_sequence.NEXTVAL, '" + spotId + "', '" + lotId + "', SYSDATE, SYSDATE + 150)";
                System.out.println(query);
                executeQuery(conn, query);

                query = "UPDATE " + getTableName() + " SET permit_id = permit_sequence.CURRVAL, request_status = 'approved', updated_by = "+operator+", updated_on = SYSDATE  where request_id = " + requestId+" AND resident_id = '"+username.trim()+"'";
                System.out.println(query);
                executeQuery(conn, query);

                return "APPROVE";
            }
        } else {
            System.out.println("Campus Student");
            // Query to get student's assigned hall id
            String housingId = lease.getHousingId();
            System.out.println(housingId);
            query = "SELECT LOT_ID from parking_resident_hall_map WHERE HOUSING_ID = '" + housingId + "'";
            ResultSet resultSet = DBAccessor.selectQuery(conn, query);
            while (resultSet.next()) {
                lotId = resultSet.getString("lot_id");
                System.out.println(lotId);
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
                    query = "UPDATE " + getTableName() + " SET request_status = 'rejected', updated_by = "+operator+", updated_on = SYSDATE  where request_id = " + requestId+" AND resident_id = '"+username.trim()+"'";
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

            query = "INSERT into PARKING_PERMIT VALUES(permit_sequence.NEXTVAL, '" + spotId + "', '" + lotId + "', SYSDATE, SYSDATE + 150)";
            System.out.println(query);
            executeQuery(conn, query);

            query = "UPDATE " + getTableName() + " SET permit_id = permit_sequence.CURRVAL, request_status = 'approved', updated_by = "+operator+", updated_on = SYSDATE  where request_id = " + requestId+" AND resident_id = '"+username.trim()+"'";
            System.out.println(query);
            executeQuery(conn, query);

            return "APPROVE";
        }
    }
}
