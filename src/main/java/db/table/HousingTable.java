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
public class HousingTable extends Table{

    @Override
    public String getTableName() {
        return "HOUSING";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + "(" +
                " housing_id "+ColumnTypes.ID_TYPE+","+
                " type VARCHAR2(32), " +
                " name VARCHAR2(32), " +
                " PRIMARY KEY (housing_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('1', 'Residence Halls', 'Gryffindor Hall')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('2', 'Residence Halls', 'Slytherin Hall')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('3', 'General Student Apartments', 'Ravenclaw')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('4', 'General Student Apartments', 'Hufflepuff')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('5', 'Family Apartments', 'Hogwarts')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
