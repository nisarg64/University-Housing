package db.table;

import pojo.Housing;
import pojo.Lease;
import pojo.LeasePreference;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 4/8/15.
 */
public class LeaseUtils {


    public static Housing getHousingDetail(Connection conn, LeasePreference preference) {

        Housing housing = null;
        String residenceType  = preference.getType().trim();
        String hallName = preference.getHallName().trim();
        String query = "";

        switch (residenceType){

            case "Residence Halls":
            case "General Student Apartments" :

                query = "SELECT * FROM ROOMS " +
                               " WHERE TYPE = '" + residenceType+ "'  AND " +
                               " PARENT_ID IN  " +
                               " (SELECT HOUSING_ID FROM HOUSING WHERE NAME = '"+ hallName+ "' ) AND " +
                               " PLACE_NUM NOT IN (SELECT LOCATION_NO FROM LEASE) ";

                try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
                    if(rs.next()){
                        housing = new Housing();
                        housing.setHousingId(rs.getString("PARENT_ID"));
                        housing.setLocationNumber(rs.getString("PLACE_NUM"));
                        housing.setName(preference.getHallName());
                        housing.setType(preference.getType());
                    }
                } catch (SQLException ex) {
                    System.err.println("Error Occurred During View Lease " + ex.getMessage());
                }

            case "Family Apartments":

                query = " SELECT F.APT_ID AS APT_ID, H.HOUSING_ID AS H_ID, H.NAME AS NAME " +
                        " FROM FAMILY_APT F, HOUSING H " +
                        " WHERE H.HOUSING_ID = F.F_APT_ID AND " +
                        " APT_ID NOT IN (SELECT LOCATION_NO FROM LEASE) ";

                try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
                    if(rs.next()){
                        housing = new Housing();
                        housing.setHousingId(rs.getString("H_ID"));
                        housing.setLocationNumber(rs.getString("APT_ID"));
                        housing.setName(rs.getString("NAME"));
                        housing.setType(preference.getType());
                    }

                } catch (SQLException ex) {
                    System.err.println("Error Occurred During View Lease " + ex.getMessage());
                }
        }

        return housing;
    }

}
