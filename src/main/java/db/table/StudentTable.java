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
public class StudentTable extends Table{

    @Override
    public String getTableName() {
        return "STUDENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " student_id CHAR(10), " +
                " category VARCHAR(6), " +
                " status VARCHAR(10), " +
                " course VARCHAR(32), " +
                " PRIMARY KEY (student_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('akagrawa', 'UG-2', '','Computer Science')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('abora', 'PG-1', '','Computer Engineering')";

        queries.add(query1);
        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}