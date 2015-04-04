package db.view;

import pojo.Staff;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 4/3/15.
 */
public class StaffView extends View {
    @Override
    public String getViewName() {
        return "STAFF_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT  " +
                " s.staff_num, s.position, s.location, " +
                " r.fname, r.lname, r.dob, r.sex, r.address_street," +
                " r.address_city, r.address_postcode, " +
                " r.primary_phone "+
                " FROM RESIDENT r " +
                " join " +
                " STAFF s " +
                " on (s.staff_num = r.res_id) ";

        DBAccessor.executeQuery(conn, query);
    }

    public Staff selectOne(Connection conn, String staffNum)  {

        Staff staff = null;
        String query = "SELECT * FROM " + getViewName() + " where staff_num = '" + staffNum + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                staff = new Staff();
                staff.setStaffNum(resultSet.getString("staff_num"));
                staff.setFname(resultSet.getString("fname"));
                staff.setLname(resultSet.getString("lname"));
                staff.setAddrStreet(resultSet.getString("address_street"));
                staff.setAddrCity(resultSet.getString("address_city"));
                staff.setPostalCode(resultSet.getString("address_postcode"));
                staff.setDob(resultSet.getDate("dob").toString());
                staff.setGender(resultSet.getString("sex"));
                staff.setPrimaryPhone(resultSet.getString("primary_phone"));
                staff.setPosition(resultSet.getString("position"));
                staff.setLocation(resultSet.getString("location"));

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During staff Profile View " + ex.getMessage());
        }
        return staff;
    }

}
