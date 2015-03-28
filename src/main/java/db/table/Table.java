package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/26/15.
 */
public abstract class Table {

      public abstract String getTableName();
      public abstract void createTable(Connection conn) throws SQLException;
      public abstract void insertIntoTable(Connection conn) throws SQLException;

      public void dropTable(Connection conn) {
            try {
                  String query = "DROP TABLE " + getTableName();
                  DBAccessor.executeQuery(conn, query);

            }catch (SQLException ex){
                  System.err.println( " Table " + getTableName() + ": " + ex.getMessage());
            }
      }
}