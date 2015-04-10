package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
                " name " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                " dob " + ColumnTypes.DATE_TYPE + " ," +
                " res_id " + ColumnTypes.ID_TYPE + " ," +
                " PRIMARY KEY (name, res_id), " +
                " FOREIGN KEY (res_id) REFERENCES RESIDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('Fleur Delacour', '31-Mar-1984', '100540007')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('Petunia Dursley', '13-Dec-1978', '100540008')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('Dudley Dursley', '26-Jun-2000', '100540008')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
