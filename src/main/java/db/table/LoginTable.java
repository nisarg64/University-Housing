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
                " role VARCHAR(10) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('akagrawa', 'abc123', 'student')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('abora', 'abc123', 'student')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('kogan', 'xyz123', 'staff')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('approval1', 'xyz123', 'guest')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('approval2', 'abc123', 'guest')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        DBAccessor.executeBatchQuery(conn, queries);
    }

    public boolean checkLogin(Login login, Connection conn) throws SQLException{

        String query = "select COUNT(*) as count from " + getTableName() + " where username = '" + login.getUsername() + "' and " +
                "password = '" + login.getPassword() + "' and role = '" + login.getRole() + "'";

        ResultSet rs = DBAccessor.selectQuery(conn, query);
        while(rs.next()){
            if(rs.getInt("count") > 0 ){
                   return true;
            }
        }
        return false;
    }

}
