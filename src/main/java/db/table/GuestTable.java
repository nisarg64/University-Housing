package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/27/15.
 */
public class GuestTable extends Table {

    @Override
    public String getTableName() {
        return "GUEST";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE "+ getTableName() + "(" +
                " approval_id CHAR(10), " +
                " status VARCHAR(10), " +
                " PRIMARY KEY (approval_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('approval1','pending')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('approval2','approved')";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}
