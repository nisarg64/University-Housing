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
public class GeneralAptProvides extends Table{

    @Override
    public String getTableName() {
        return "GENERAL_APT_PROVIDES";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE GENERAL_APT_PROVIDES (" +
                " g_apartment_no VARCHAR(20), " +
                " place_num VARCHAR(20), " +
                " PRIMARY KEY (g_apartment_no, place_num), " +
                " FOREIGN KEY (g_apartment_no) REFERENCES GENERAL_APT, " +
                " FOREIGN KEY (place_num) REFERENCES ROOM " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G1', 'G1_O1')";
        String query2 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G1', 'G1_O2')";
        String query3 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G1', 'G1_O3')";
        String query4 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G1', 'G1_O4')";
        String query5 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G1', 'G1_O5')";

        String query6 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G2', 'G2_02')";
        String query7 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G3', 'G3_03')";
        String query8 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G4', 'G4_04')";
        String query9 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G5', 'G5_05')";
        String query10 = "INSERT INTO GENERAL_APT_PROVIDES VALUES('G6', 'G6_06')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    @Override
    public void dropTable(Connection conn){

        try {
            String query = "DROP TABLE RESIDENT_HALL";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  RESIDENT_HALL : " + ex.getMessage());
        }
    }
}
