package db.view;

import pojo.Invoice;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nisarg on 4/3/15.
 */
public class StaffNameView extends View{



    @Override
    public String getViewName() {
        return "STAFF_NAME_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {

        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT * FROM STAFF" ;

        DBAccessor.executeQuery(conn, query);
    }

    public String getStaffName(Connection conn, String staffId) {
        String staff_name = "";
        String query = "SELECT * FROM " + getViewName() + " where staff_num = '" + staffId + "'";
        System.out.println(query);
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()) {
                staff_name = resultSet.getString("FNAME") + " " + resultSet.getString("LNAME");
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Staff View " + ex.getMessage());
        }

        System.out.println(staff_name);
        return staff_name;
    }
}
