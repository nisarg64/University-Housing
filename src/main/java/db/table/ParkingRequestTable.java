package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class ParkingRequestTable extends Table {

    @Override
    public String getTableName() {
        return "PARKINGREQUEST";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "request_id varchar(32), "+
                "resident_id char(10), "+
                "vehicle_type varchar(32), "+
                "isHandicapped char(1), "+
                "nearby_spot_preference char(1), "+
                "request_status varchar(32), "+
                "permit_id varchar(32), " +
                "PRIMARY KEY (request_id, resident_id), "+
                "FOREIGN KEY (resident_id) REFERENCES RESIDENT(res_id), "+
                "FOREIGN KEY (permit_id) REFERENCES PARKINGPERMIT(permit_id) )";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
