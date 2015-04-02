package db.view;

import pojo.Guest;
import pojo.Student;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class GuestView  extends View{
    @Override
    public String getViewName() {
        return "GUEST_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {

        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT  " +
                " g.approval_id, g.status, " +
                " r.fname, r.lname, r.dob, r.sex, r.address_street," +
                " r.address_city, r.address_postcode, " +
                " r.primary_phone, r.spl_needs "+
                " FROM   RESIDENT r " +
                " join " +
                " GUEST g " +
                " on (g.approval_id = r.res_id) ";
        DBAccessor.executeQuery(conn, query);
    }

    public Guest selectOne(Connection conn, String approvalId)  {

        Guest guest = null;
        String query = "SELECT * FROM " + getViewName() + " where approval_id = " + approvalId;
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                guest = new Guest();
                guest.setApprovalId(resultSet.getString("approval_id"));
                guest.setName(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                guest.setAddrStreet(resultSet.getString("address_street"));
                guest.setAddrCity(resultSet.getString("address_city"));
                guest.setPostalCode(resultSet.getString("address_postcode"));
                guest.setStatus(resultSet.getString("status"));
                guest.setGender(resultSet.getString("sex"));
                guest.setPrimaryPhone(resultSet.getString("primary_phone"));
                guest.setSpclNeeds(resultSet.getString("spl_needs"));

                System.out.println(guest);

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Login " + ex.getMessage());
        }
        return guest;
    }
}
