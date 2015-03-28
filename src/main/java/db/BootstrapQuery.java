package db;
import util.*;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

import static util.DBAccessor.executeQuery;

/**
 * Author : abhishek
 * Created on 3/19/15.
 */
public class BootstrapQuery {

    private DBAccessor dbAccessor;

    public BootstrapQuery(){
        dbAccessor = new DBAccessor();
    }

    public void createAllTables(){

        Connection conn = null;
        try{
            conn = dbAccessor.getConnection();
            dropAllTables(conn);


        //    createLoginTable(conn);
        //    createResidentTable(conn);
            createHousingOptions(conn);
            createParkingLotTable(conn);
            createParkingSpotTable(conn);


        // Login Table
          createLoginTable(conn);

        /*
            Student Related Tables
        */
        //      createResidentTable(conn);

        //    createStudentTable(conn);
        //    createGuestTable(conn);
        //    createFamilyTable(conn);
        //    createKinTable(conn);


         //   insertIntoTables(conn);

        /*
              Housing Queries
        */

            insertIntoTables(conn);


            conn.commit();
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

    private void createHousingOptions(Connection conn) throws SQLException {
        String query = "CREATE TABLE HOUSING_OPTIONS ("+
                        "housing_id varchar(32), "+
                        "housing_name varchar(75), "+
                        "housing_address varchar(100), "+
                        "PRIMARY KEY (housing_id))";
        executeQuery(conn, query);
    }

    private void createParkingSpotTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE PARKINGSPOT ("+
                        "spot_id varchar(32), "+
                        "lot_id varchar(32), "+
                        "spot_type varchar(32), "+
                        "availability char(1), " +
                        "rental_fee float(6), "+
                        "PRIMARY KEY (spot_id), "+
                        "FOREIGN KEY (lot_id) REFERENCES PARKINGLOT(lot_id) )";
        executeQuery(conn, query);
    }

    private void createParkingLotTable(Connection conn) throws SQLException {
        String query =  "CREATE TABLE PARKINGLOT ("+
                        "lot_id varchar(32), "+
                        "lot_type varchar(32), "+
                        "nearby_housing_id varchar(32), "+
                        "PRIMARY KEY (lot_id), "+
                        "FOREIGN KEY (nearby_housing_id) REFERENCES HOUSING_OPTIONS(housing_id) )";
        executeQuery(conn, query);
    }

    public void insertIntoTables(Connection conn) throws SQLException{

       // insertIntoResidentTable(conn);

    }

    private void dropAllTables(Connection conn) throws SQLException{

        //String query = "DROP TABLE RESIDENT";
        String query = "DROP TABLE PARKINGSPOT";
        executeQuery(conn, query);
        query = "DROP TABLE PARKINGLOT";
        executeQuery(conn, query);
        query = "DROP TABLE HOUSING_OPTIONS";
        executeQuery(conn, query);
    }

    private void createLoginTable(Connection conn) throws Exception{
        String query = " CREATE TABLE LOGIN (" +
                       " username VARCHAR(32), " +
                       " password VARCHAR(32), " +
                       " role VARCHAR(10) ";
        executeQuery(conn, query);
    }

    private void createResidentTable(Connection conn) throws SQLException{

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
        executeQuery(conn, query);
    }

    private void insertIntoResidentTable(Connection conn) throws SQLException{

        String query1 = "INSERT INTO RESIDENT VALUES('akagrawa', 'Abhishek', 'Agrawal', 'M', '02-Jul-1990','1234 avent ferry'," +
                       "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";
        String query2 = "INSERT INTO RESIDENT VALUES('abora', 'Anand', 'Bora', 'M', '07-Jul-1990','1234 avent ferry'," +
                "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";

        executeQuery(conn, query1);
        executeQuery(conn, query2);
    }

    private void createStudentTable(Connection conn) throws SQLException {

        String query = " CREATE TABLE STUDENT (" +
                " student_id CHAR(10), " +
                " category VARCHAR(6), " +
                " status VARCHAR(10), " +
                " course VARCHAR(32) " +
                " PRIMARY KEY (student_id) " +
                ")";
        executeQuery(conn, query);
    }

    private void createGuestTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE GUEST(" +
                " approval_id CHAR(10), " +
                " status VARCHAR(10), " +
                " PRIMARY KEY (approval_id) " +
                ")";
        executeQuery(conn, query);
    }


    private void createFamilyTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE FAMILY(" +
                " name VARCHAR(32), " +
                " dob DATE, " +
                " relation VARCHAR(12), " +
                " student_id CHAR(10), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    private void createKinTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE KIN(" +
                " name VARCHAR(32), " +
                " relation VARCHAR(12), " +
                " address_street VARCHAR(100), " +
                " address_city VARCHAR(32), " +
                " address_postcode VARCHAR(10), " +
                " contact_no VARCHAR(12), " +
                " student_id CHAR(10), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    public static void main(String[] args) throws Exception{
        BootstrapQuery bootstrapQuery = new BootstrapQuery();
        bootstrapQuery.createAllTables();

        //bootstrapQuery.dbAccessor.executeQuery("select table_name from user_tables");

        //bootstrapQuery.dbAccessor.executeQuery("SELECT fname, lname FROM RESIDENT ");

    }

}
