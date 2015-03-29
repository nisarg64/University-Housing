package db.table;

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
public class ResidentTable extends Table {

    @Override
    public String getTableName() {
        return "RESIDENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE " + getTableName() + " (" +
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

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('akagrawa', 'Abhishek', 'Agrawal', 'M', '02-Jul-1990','1234 avent ferry'," +
                "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";

        String query2 = "INSERT INTO " + getTableName() + " VALUES('abora', 'Anand', 'Bora', 'M', '07-Jul-1990','1234 avent ferry'," +
                "'Raleigh','27606','Indian','9190000000','9190000000','NO','XYZ','ABC')";

        queries.add(query1);
        queries.add(query2);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    public List<Resident> selectAll(Connection conn) throws SQLException{

        String query = "SELECT * from " + getTableName();
        List<Resident> residents = new LinkedList<>();
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
