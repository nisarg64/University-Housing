package util;

import db.table.ResidentTable;
import pojo.Resident;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

/**
 * Author : abhishek
 * Created on 4/6/15.
 */
public class RoommateFinder {

    public List<Resident> findAllMatchRoommates(Connection conn, String residentId){

        List<Resident> residents = null;
        ResidentTable residentTable = new ResidentTable();
        Resident hostResident = residentTable.selectOne(conn, residentId);
        residents = residentTable.selectMatch(conn, hostResident);

        filterFurther(residents, hostResident); //It will filter non-matching roommates further

        //TODO:: Give in priority order

        return residents;
    }

    private void filterFurther(List<Resident> residents, Resident hostResident) {

        ListIterator<Resident> residentItr = residents.listIterator();
        while(residentItr.hasNext()){
            Resident potentialRoommate = residentItr.next();
            if(potentialRoommate.getCategory().equalsIgnoreCase("visitor") &&
                    !hostResident.getCategory().equalsIgnoreCase("visitor")){
                residentItr.remove();
            }

            if(!potentialRoommate.getCategory().equalsIgnoreCase("visitor") &&
                    hostResident.getCategory().equalsIgnoreCase("visitor")){
                residentItr.remove();
            }

            //String query = "SELECT count(*) from LEASE_REQUEST where RES_ID = '"+potentialRoommate.getResId()+"' AND STATUS = 'InProgress'";
            //ResultSet resultSet =
        }
    }

    public static void main(String[] args) {

        Connection conn = null;
        try{
            conn = DBAccessor.getConnection();
            RoommateFinder roommateFinder = new RoommateFinder();
            List<Resident> roommates = roommateFinder.findAllMatchRoommates(conn, "100540001");
            System.out.println("Roommate For Harry Potter " );

            for(Resident resident : roommates){
                System.out.println(resident);
            }

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

}
