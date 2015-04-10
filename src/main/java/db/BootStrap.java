package db;

import db.table.*;
import db.view.*;
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
        database.addTable(new StaffTable());
        database.addTable(new KinTable());
        database.addTable(new ParkingLotTable());

        database.addTable(new ParkingSpotTable());
        database.addTable(new ParkingPermitTable());
        database.addTable(new ParkingRequestTable());

        database.addTable(new HousingTable());
        database.addTable(new ApartmentTable());
        database.addTable(new GeneralAptTable());
        database.addTable(new HasFamilyAptTable());
        database.addTable(new ResidentHallTable());
        database.addTable(new HasRoomsTable());

        database.addTable(new PrivateAccoTable());
        database.addTable(new LeaseRequestTable());
        database.addTable(new LeaseTable());
        database.addTable(new LeasePreferenceTable());
        database.addTable(new LeaseTerminationRequestTable());
        database.addView(new LeaseView());
        database.addView(new LeaseTerminationRequestView());

        database.addTable(new ParkingResidentHallMapTable());
        database.addTable(new TicketSeverityTable());
        database.addTable(new MaintenanceTicketTable());

//        database.addTable(new InvoicePaymentTable());
        database.addTable(new InvoiceTable());
        database.addView(new InvoiceView());
        database.addView(new ParkingView());
        database.addView(new StaffNameView());

        database.makeAll();

        System.out.println("Done!");
    }



    public static void generalQuery(){
        Connection conn = null;
        try{
            conn = DBAccessor.getConnection();

            InvoiceTable invoiceTable = new InvoiceTable();
            invoiceTable.insertFormerInvoices(conn, "100540001");
            //ParkingRequestTable parkingRequestTable = new ParkingRequestTable();
            //System.out.println(parkingRequestTable.checkParkingAvailability(conn, "1002"));
           // System.out.println(parkingRequestTable.selectAll(conn));

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
