package db;

import db.table.*;
import db.view.GuestView;
import db.view.StaffView;
import db.view.LeaseView;
import db.view.StudentView;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/27/15.
 */
public class BootStrap {

    public static void bootStrap() {

        Database database = new Database();
        database.addTable(new LoginTable());
        database.addTable(new ResidentTable());
        database.addTable(new StudentTable());
        database.addTable(new GuestTable());
        database.addTable(new StaffTable());
        database.addTable(new ParkingLotTable());

        database.addTable(new ParkingSpotTable());
        database.addTable(new ParkingPermitTable());
        database.addTable(new ParkingRequestTable());

        database.addView(new StudentView());
        database.addView(new GuestView());
        database.addView(new StaffView());

        database.addTable(new RoomTable());
        database.addTable(new ResidentHallTable());
        database.addTable(new ResidentHallProvides());
        database.addTable(new ApartmentTable());
        database.addTable(new GeneralAptTable());
        database.addTable(new GeneralAptProvides());
        database.addTable(new FamilyAptTable());
        database.addTable(new PrivateAccoTable());
        database.addTable(new LeaseTable());
        database.addTable(new LeasePreferenceTable());
        database.addTable(new LeaseTerminationRequest());
        database.addView(new LeaseView());

        database.addTable(new ParkingResidentHallMapTable());
        database.addTable(new TicketSeverityTable());
        database.addTable(new MaintenanceTicketTable());

        database.makeAll();
        System.out.println("Done!");
    }

    public static void generalQuery(){
        Connection conn = null;
        try{
            conn = DBAccessor.getConnection();

            ParkingRequestTable parkingRequestTable = new ParkingRequestTable();
            System.out.println(parkingRequestTable.selectAll(conn));

            //staffView.createView(conn);
            /*StudentView studentView = new StudentView();
            studentView.dropView(conn);
            studentView.createView(conn);
            System.out.println(studentView.selectOne(conn, "akagrawa"));
*/
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //generalQuery();
       bootStrap();
    }
}
