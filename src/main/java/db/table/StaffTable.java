package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 4/3/15.
 */
public class StaffTable extends Table {

    @Override
    public String getTableName() {
        return "STAFF";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " staff_num CHAR(10), " +
                " position VARCHAR2(20), " +
                " location VARCHAR2(20), " +
                " PRIMARY KEY (staff_num) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('kogan', 'HALL MANAGER', 'HID1' )";

        queries.add(query1);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}
