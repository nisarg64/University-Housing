package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * User: Nikhil
 * Date: 08-03-15
 * Time: 06:54 PM
 */
public class DBAccessor {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
    private static final String username = "ndgandh2";      //unity id
    private static final String password = "200062400";      //9 digit student id

    private Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public void executeUpdateSQL(String sql) throws Exception {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public void executeQuery(String sql) throws Exception {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String s = rs.getString("COF_NAME");
            float n = rs.getFloat("PRICE");
            System.out.println(s + "   " + n);
        }
        statement.close();
    }

    public static void main(String[] args) throws Exception {
        DBAccessor dbAccessor = new DBAccessor();

        dbAccessor.executeUpdateSQL("CREATE TABLE COFFEES (COF_NAME VARCHAR(32), SUP_ID INTEGER, " +
                "PRICE FLOAT, SALES INTEGER, TOTAL INTEGER)");
        dbAccessor.executeUpdateSQL("INSERT INTO COFFEES VALUES ('Colombian', 101, 7.99, 0, 0)");
        dbAccessor.executeUpdateSQL("INSERT INTO COFFEES VALUES ('French_Roast', 49, 8.99, 0, 0)");

        dbAccessor.executeQuery("SELECT COF_NAME, PRICE FROM COFFEES");
        dbAccessor.executeUpdateSQL("DROP TABLE COFFEES");
    }
}