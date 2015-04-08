package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : anand
 * Created on 3/27/15.
 */
public class ResidentHallTable extends Table{

    public static final String HALL_ID = "hall_id";
    public static final String TABLE_NAME = "RESIDENT_HALL";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() +" (" +
                " hall_id " + ColumnTypes.ID_TYPE + " ," +
                " num_room number, " +
                " student_type varchar2(20), " +
                " PRIMARY KEY (hall_id), " +
                " FOREIGN KEY (hall_id) REFERENCES HOUSING " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('1', " +
                " 5, " +
                " null " +
                " )";

        String query2 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('2', " +
                " 3, " +
                " 'Graduate' " +
                " )";

        queries.add(query1);
        queries.add(query2);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
