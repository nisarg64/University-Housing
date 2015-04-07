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
                  System.out.println("TABLE DROPPED SUCCESSFULLY [" +  getTableName() + "] ");
                  System.out.println("--------------------------------------------------------");

            }catch (SQLException ex){
                  System.err.println( " Table " + getTableName() + ": " + ex.getMessage());
            }
      }

      public final String createInsertPreparedStatement(String tableName, int valuesCount, String... columns) {
            StringBuilder str = new StringBuilder();
            str.append("INSERT INTO ").append(tableName);
            if (columns != null) {
                  str.append("(");
                  valuesCount = columns.length;
                  for (int i = 1; i <= valuesCount; ++i) {
                        str.append(columns[i - 1]);
                        if (i < valuesCount) {
                              str.append(", ");
                        }
                  }
                  str.append(")");
            }

            str.append(" VALUES(");
            for (int i = 1; i <= valuesCount; ++i) {
                  str.append("?");
                  if (i < valuesCount) {
                        str.append(", ");
                  }
            }
            str.append(")");
            return str.toString();
      }

      public String createInsertQuery(String tableName, String... values) {
            StringBuilder str = new StringBuilder("INSERT INTO ").append(tableName);
            str.append(" VALUES(");
            for (int i = 1; i <= values.length; ++i) {
                  str.append(values[i - 1]);
                  if (i < values.length) {
                        str.append(", ");
                  }
            }
            str.append(")");
            return str.toString();
      }


}