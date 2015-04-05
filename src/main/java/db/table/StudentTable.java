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
                " student_id VARCHAR2(20), " +
                " category VARCHAR(10) NOT NULL, " +
                " status VARCHAR(10), " +
                " course VARCHAR(32) NOT NULL, " +
                " PRIMARY KEY (student_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('akagrawa', 'Senior', '','Computer Science')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('tomhanks', 'Junior', '','Electrical Science')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('rdravid', 'Freshman', '','Social Sciences')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('bpitt', 'Sophomore', '','Computer Science')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('abora', 'Graduate', '','Computer Engineering')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}