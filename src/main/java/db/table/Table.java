package db.table;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/26/15.
 */
public interface Table {

      public void createTable(Connection conn) throws SQLException;
      public void insertIntoTable(Connection conn) throws SQLException;
      public void dropTable(Connection conn);

}
