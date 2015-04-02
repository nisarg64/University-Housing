
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
public class FamilyAptTable extends Table{

    @Override
    public String getTableName() {
        return "FAMILY_APT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + "(" +
                " f_apartment_no VARCHAR(20), " +
                " num_of_bedrooms INTEGER, " +
                " monthly_rent INTEGER, " +
                " PRIMARY KEY (f_apartment_no) " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('F1', 2, 1500)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('F2', 3, 1600)";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('F3', 2, 1200)";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('F4', 4, 2000)";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('F5', 2, 1000)";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('F6', 3, 1800)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
