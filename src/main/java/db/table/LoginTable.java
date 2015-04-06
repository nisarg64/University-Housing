package db.table;

import pojo.Login;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/27/15.
 */
public class LoginTable extends Table{

    @Override
    public String getTableName() {
        return "LOGIN";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " username VARCHAR(32), " +
                " password VARCHAR(32), " +
                " role VARCHAR(10), " +
                " PRIMARY KEY (username)"+
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('100540001', 'abc123', 'student')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('100540002', 'abc123', 'student')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('100540003', 'abc123', 'student')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('100540004', 'abc123', 'student')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('100540005', 'abc123', 'student')";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('100540006', 'abc123', 'student')";
        String query7 = "INSERT INTO " + getTableName() + " VALUES('100540007', 'abc123', 'student')";
        String query8 = "INSERT INTO " + getTableName() + " VALUES('100540008', 'abc123', 'student')";

        String query9 = "INSERT INTO " + getTableName() + " VALUES('200540001', 'abc123', 'guest')";
        String query10 = "INSERT INTO " + getTableName() + " VALUES('200540002', 'abc123', 'guest')";

        String query11 = "INSERT INTO " + getTableName() + " VALUES('300220001', 'abc123', 'staff')";
        String query12 = "INSERT INTO " + getTableName() + " VALUES('300220002', 'abc123', 'staff')";
        String query13 = "INSERT INTO " + getTableName() + " VALUES('300220003', 'abc123', 'staff')";
        String query14 = "INSERT INTO " + getTableName() + " VALUES('300220004', 'abc123', 'staff')";
        String query15 = "INSERT INTO " + getTableName() + " VALUES('300220005', 'abc123', 'staff')";


        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);
        queries.add(query11);
        queries.add(query12);
        queries.add(query13);
        queries.add(query14);
        queries.add(query15);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    public boolean checkLogin(Connection conn, Login login){

        try{
            String query = "select COUNT(*) as count from " + getTableName() + " where username = '" + login.getUsername() + "' and " +
                    "password = '" + login.getPassword() + "' and role = '" + login.getRole() + "'";

            ResultSet rs = DBAccessor.selectQuery(conn, query);
            while(rs.next()){
                if(rs.getInt("count") > 0 ){
                    return true;
                }
            }
        }catch (SQLException ex){

            System.err.println("Error Occurred During Login " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

}
