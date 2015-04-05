package db.table;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class FamilyTable extends Table {

    @Override
    public String getTableName() {
        return "FAMILY";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + "( " +
                " name VARCHAR2(32), " +
                " dob DATE, " +
                " relation VARCHAR(12), " +
                " student_id VARCHAR2(20), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }
}
