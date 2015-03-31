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
public class GeneralAptTable extends Table{

    @Override
    public String getTableName() {
        return "GENERAL_APT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + "(" +
                " g_apartment_no VARCHAR(20), " +
                " num_of_bedrooms INTEGER, " +
                " num_of_bathrooms INTEGER, " +
                " PRIMARY KEY (g_apartment_no) " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('G1', 2, 2)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('G2', 2, 1)";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('G3', 3, 2)";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('G4', 3, 3)";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('G5', 4, 4)";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('G6', 4, 3)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
