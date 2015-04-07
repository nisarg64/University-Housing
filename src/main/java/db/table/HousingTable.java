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

                " address VARCHAR2(150), " +
                " manager " + ColumnTypes.ID_TYPE + " ," +
                " tel_number VARCHAR2(12), " +

                " PRIMARY KEY (housing_id), " +
                " FOREIGN KEY (manager) REFERENCES STAFF " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('1', 'Residence Halls', 'Gryffindor Hall', " +
                " '2751 Cates Avenue, Gryffindor Residence Hall, Raleigh, NC 27607', " +
                " '300220001', " +
                " '919 540 001' " +
                " )";

        String query2 = "INSERT INTO " + getTableName() + " VALUES('2', 'Residence Halls', 'Slytherin Hall', " +
                " '210 Dan Allen Dr, Raleigh, NC 27695', " +
                " '300220002', " +
                " '919 540 002' " +
                " )";

        String query3 = "INSERT INTO " + getTableName() + " VALUES('3', 'General Student Apartments', 'Ravenclaw', " +
                " '315, Gorman Crossings, Raleigh - 27606, NC', " +
                " '300220003', " +
                " '919 540 003' " +
                " )";

        String query4 = "INSERT INTO " + getTableName() + " VALUES('4', 'General Student Apartments', 'Hufflepuff', " +
                " '515, University Commons, Raleigh - 27606, NC', " +
                " '300220004', " +
                " '919 540 004' " +
                " )";

        String query5 = "INSERT INTO " + getTableName() + " VALUES('5', 'Family Apartments', 'Hogwarts', " +
                " '300, Wade Avenue, Raleigh - 27606, NC', " +
                " '300220005', " +
                " '919 540 005' " +
                " )";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
