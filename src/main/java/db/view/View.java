package db.view;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public abstract class View {

    public abstract String getViewName();
    public abstract void createView(Connection conn) throws SQLException;

    public void dropView(Connection conn) {
        try {
            String query = "DROP VIEW " + getViewName();
            DBAccessor.executeQuery(conn, query);
            System.out.println("VIEW DROPPED SUCCESSFULLY [" +  getViewName() + "] ");
            System.out.println("--------------------------------------------------------");

        }catch (SQLException ex){
            System.err.println( " VIEW " + getViewName() + ": " + ex.getMessage());
        }
    }

}
