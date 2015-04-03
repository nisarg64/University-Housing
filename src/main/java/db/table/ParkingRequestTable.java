package db.table;

import pojo.ParkingRequest;

import java.sql.Connection;
import java.sql.SQLException;

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
}
