package db.table;

import db.table.Table;
import pojo.Resident;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/26/15.
 */
public class ResidentTable implements Table {

    @Override
    public void createTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE RESIDENT (" +
                " res_id CHAR(10), " +
                " fname VARCHAR(32), " +
                " lname VARCHAR(32) NOT NULL, " +
                " sex CHAR(1), " +
                " dob DATE, " +
                " address_street VARCHAR(100), " +
                " address_city VARCHAR(32), " +
                " address_postcode VARCHAR(10), " +
                " nationality VARCHAR(32), " +
                " primary_phone VARCHAR(12), " +
                " secondary_phone VARCHAR(12), " +
                " smoker CHAR(2), " +
                " comments VARCHAR(32), " +
                " spl_needs VARCHAR(32), " +
                " PRIMARY KEY (res_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<String>();
        String query1 = "INSERT INTO RESIDENT VALUES('akagrawa', 'Abhishek', 'Agrawal', 'M', '02-Jul-1990','1234 avent ferry'," +
                "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";

        String query2 = "INSERT INTO RESIDENT VALUES('abora', 'Anand', 'Bora', 'M', '07-Jul-1990','1234 avent ferry'," +
                "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";

        queries.add(query1);
        queries.add(query2);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE RESIDENT";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  RESIDENT : " + ex.getMessage());
        }
    }

    public List<Resident> selectAll(Connection conn) throws SQLException{

        String query = "SELECT * from RESIDENT";
        List<Resident> residents = new LinkedList<Resident>();
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                Resident resident = new Resident();
                resident.setRes_id(resultSet.getString("res_id"));
                resident.setFname(resultSet.getString("fname"));
                resident.setLname(resultSet.getString("lname"));

                System.out.println(resident);
                residents.add(resident);
            }
        }
        return residents;
    }

}