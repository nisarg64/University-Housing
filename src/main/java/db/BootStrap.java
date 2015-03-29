package db;

import db.table.*;
import db.view.StudentView;
import pojo.Login;
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
        database.addTable(new HousingOptionsTable());
        database.addTable(new ParkingLotTable());
        database.addTable(new ParkingSpotTable());
        database.addTable(new ParkingPermitTable());
        database.addTable(new ParkingRequestTable());

        database.addView(new StudentView());

        /*database.addTable(new RoomTable());
        database.addTable(new ResidentHallTable());
        database.addTable(new ResidentHallProvides());
        database.addTable(new ApartmentTable());
        database.addTable(new GeneralAptTable());
        database.addTable(new GeneralAptProvides());
        database.addTable(new FamilyAptTable());
        database.addTable(new PrivateAccoTable());*/

        database.makeAll();
        System.out.println("Done!");
    }

    public static void generalQuery(){
        Connection conn = null;
        try{
            conn = DBAccessor.getConnection();

            /*ResidentTable residentTable = new ResidentTable();
            residentTable.selectAll(conn);
            LoginTable loginTable = new LoginTable();
            Login login = new Login();
            login.setUsername("akagrawa");
            login.setPassword("abc123");
            login.setRole("student");
            System.out.println(loginTable.checkLogin(login, conn));
*/

            StudentView studentView = new StudentView();
            studentView.dropView(conn);
            studentView.createView(conn);
            System.out.println(studentView.selectOne(conn, "akagrawa"));

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
        generalQuery();
     //   bootStrap();
    }
}
