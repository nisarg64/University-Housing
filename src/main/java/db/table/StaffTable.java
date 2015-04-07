package db.table;

import pojo.Staff;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/3/15.
 */
public class StaffTable extends Table {

    @Override
    public String getTableName() {
        return "STAFF";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " staff_num " + ColumnTypes.ID_TYPE + " ," +
                " fname " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                " lname " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " NOT NULL ," +
                " sex " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " dob " + ColumnTypes.DATE_TYPE + " ," +
                " address_street " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " ," +
                " address_city " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " address_postcode " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                " address_country " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " position " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                " work_location " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " PRIMARY KEY (staff_num) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('300220001','Minerva','McGonagall', 'Female','04-Oct-1950', " +
                "'McGonagall Road',  'Delhi', '011','India', 'Hall Manager/Supervisor', 'Gryffindor Hall' )";

        String query2 = "INSERT INTO " + getTableName() + " VALUES('300220002','Severus','Snape', 'Male','07-Sep-1953', " +
                "'Snape Road',  'Lahore', '007','Pakistan', 'Hall Manager/Supervisor', 'Slytherin Hall' )";

        String query3 = "INSERT INTO " + getTableName() + " VALUES('300220003','Filius','Flitwick', 'Male','06-Oct-1960', " +
                "'Flitwick Road',  'Sydney', '050','Australia', 'Hall Manager/Supervisor', 'Ravenclaw' )";

        String query4 = "INSERT INTO " + getTableName() + " VALUES('300220004','Pomona','Sprout', 'Female','16-Oct-1965', " +
                "'Sprout Road',  'Berlin', '05963','Germany', 'Hall Manager/Supervisor', 'Hufflepuff' )";

        String query5 = "INSERT INTO " + getTableName() + " VALUES('300220005','Albus','Dumbledore', 'Male','23-Apr-1945', " +
                "'Dumbledore Road',  'Gandhinagar', '382007','India', 'Hall Manager/Supervisor', 'Hogwarts' )";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);


        DBAccessor.executeBatchQuery(conn, queries);
    }

    public Staff selectOne(Connection conn, String staffNum)  {

        Staff staff = null;
        String query = "SELECT * FROM " + getTableName() + " where staff_num = '" + staffNum + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                staff = new Staff();
                staff.setStaffNum(resultSet.getString("staff_num"));
                staff.setFname(resultSet.getString("fname"));
                staff.setLname(resultSet.getString("lname"));
                staff.setAddrStreet(resultSet.getString("address_street"));
                staff.setAddrCity(resultSet.getString("address_city"));
                staff.setAddrCountry(resultSet.getString("address_country"));
                staff.setPostalCode(resultSet.getString("address_postcode"));
                staff.setDob(resultSet.getDate("dob").toString());
                staff.setGender(resultSet.getString("sex"));
                staff.setPosition(resultSet.getString("position"));
                staff.setWorkLocation(resultSet.getString("work_location"));

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During staff Profile View " + ex.getMessage());
        }
        return staff;
    }
}
