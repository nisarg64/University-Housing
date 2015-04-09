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
public class HasRoomsTable extends Table {

    @Override
    public String getTableName() {
        return "ROOMS";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " parent_id "+ColumnTypes.ID_TYPE+","+
                " apt_id "+ColumnTypes.ID_TYPE+","+
                " type VARCHAR2(32), " +
                " place_num VARCHAR(20), " +
                " room_num VARCHAR(20), " +
                " monthly_rent number, " +
                " security_deposit number, " +
                " PRIMARY KEY (parent_id, place_num), " +
                " FOREIGN KEY (parent_id) REFERENCES HOUSING " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('1', null, 'Residence Halls', '001', '001', 400, 400)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('1', null, 'Residence Halls', '002', '002', 400, 400)";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('1', null, 'Residence Halls', '003', '003', 400, 400)";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('1', null, 'Residence Halls', '004', '004', 400, 400)";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('1', null, 'Residence Halls', '005', '005', 400, 400)";

        String query6 = "INSERT INTO " + getTableName() + " VALUES('2', null, 'Residence Halls', '006', '001', 450, 500)";
        String query7 = "INSERT INTO " + getTableName() + " VALUES('2', null, 'Residence Halls', '007', '002', 450, 500)";
        String query8 = "INSERT INTO " + getTableName() + " VALUES('2', null, 'Residence Halls', '008', '003', 450, 500)";

        String query12 = "INSERT INTO " + getTableName() + " VALUES('3', '101', 'General Student Apartments', '001', '001', 350, 400)";
        String query13 = "INSERT INTO " + getTableName() + " VALUES('3', '101', 'General Student Apartments', '002', '002', 350, 400)";
        String query14 = "INSERT INTO " + getTableName() + " VALUES('3', '101', 'General Student Apartments', '003', '003', 350, 400)";

        String query15 = "INSERT INTO " + getTableName() + " VALUES('3', '102', 'General Student Apartments', '004', '001', 350, 400)";
        String query16 = "INSERT INTO " + getTableName() + " VALUES('3', '102', 'General Student Apartments', '005', '002', 350, 400)";
        String query17 = "INSERT INTO " + getTableName() + " VALUES('3', '102', 'General Student Apartments', '006', '003', 350, 400)";
        String query18 = "INSERT INTO " + getTableName() + " VALUES('3', '102', 'General Student Apartments', '007', '004', 350, 400)";

        String query19 = "INSERT INTO " + getTableName() + " VALUES('4', '103', 'General Student Apartments', '008', '001', 375, 450)";
        String query20 = "INSERT INTO " + getTableName() + " VALUES('4', '103', 'General Student Apartments', '009', '002', 375, 450)";
        String query21 = "INSERT INTO " + getTableName() + " VALUES('4', '103', 'General Student Apartments', '010', '003', 375, 450)";

        String query22 = "INSERT INTO " + getTableName() + " VALUES('4', '104', 'General Student Apartments', '011', '001', 375, 450)";
        String query23 = "INSERT INTO " + getTableName() + " VALUES('4', '104', 'General Student Apartments', '012', '002', 375, 450)";
        String query24 = "INSERT INTO " + getTableName() + " VALUES('4', '104', 'General Student Apartments', '013', '003', 375, 450)";
        String query25 = "INSERT INTO " + getTableName() + " VALUES('4', '104', 'General Student Apartments', '014', '004', 375, 450)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query12);
        queries.add(query13);
        queries.add(query14);
        queries.add(query15);
        queries.add(query16);
        queries.add(query17);
        queries.add(query18);
        queries.add(query19);
        queries.add(query20);
        queries.add(query21);
        queries.add(query22);
        queries.add(query23);
        queries.add(query24);
        queries.add(query25);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
