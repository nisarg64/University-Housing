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

    @Override
    public String getTableName() {
        return "APARTMENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE APARTMENT(" +
                " apartment_no VARCHAR(20), " +
                " address VARCHAR(32), " +
                " PRIMARY KEY (apartment_no) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO APARTMENT VALUES('AID1', 'Avent Ferry Road')";
        String query2 = "INSERT INTO APARTMENT VALUES('AID2', 'Hills Road')";
        String query3 = "INSERT INTO APARTMENT VALUES('AID3', 'State Road')";
        String query4 = "INSERT INTO APARTMENT VALUES('AID4', 'MG Road')";
        String query5 = "INSERT INTO APARTMENT VALUES('AID5', 'Park Avenue')";
        String query6 = "INSERT INTO APARTMENT VALUES('AID6', 'Cross Road')";

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
            String query = "DROP TABLE APARTMENT";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  APARTMENT : " + ex.getMessage());
        }
    }
}
