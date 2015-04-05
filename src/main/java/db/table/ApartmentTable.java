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
public class ApartmentTable extends Table{

    public static final String APARTMENT_NO = "apartment_no";
    public static final String TABLE_NAME = "APARTMENT";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + TABLE_NAME + "(" +
                " " + APARTMENT_NO + " VARCHAR(20), " +
                " address VARCHAR(32), " +
                " PRIMARY KEY (" + APARTMENT_NO + ") " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + TABLE_NAME + " VALUES('AID1', 'Avent Ferry Road')";
        String query2 = "INSERT INTO " + TABLE_NAME + " VALUES('AID2', 'Hills Road')";
        String query3 = "INSERT INTO " + TABLE_NAME + " VALUES('AID3', 'State Road')";
        String query4 = "INSERT INTO " + TABLE_NAME + " VALUES('AID4', 'MG Road')";
        String query5 = "INSERT INTO " + TABLE_NAME + " VALUES('AID5', 'Park Avenue')";
        String query6 = "INSERT INTO " + TABLE_NAME + " VALUES('AID6', 'Cross Road')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE " + TABLE_NAME;
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  " + TABLE_NAME + " : " + ex.getMessage());
        }
    }
}
