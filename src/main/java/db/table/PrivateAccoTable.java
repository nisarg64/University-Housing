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
public class PrivateAccoTable extends Table{

    @Override
    public String getTableName() {
        return "PRIVATE_ACCO";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE PRIVATE_ACCO(" +
                " student_id CHAR(20), " +
                " student_category VARCHAR(20), " +
                " address VARCHAR(20), " +
                " phone_num VARCHAR(12), " +
                " PRIMARY KEY (student_id) " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO PRIVATE_ACCO VALUES('200045256', 'UG1', 'Park road', '919-666-1001')";
        String query2 = "INSERT INTO PRIVATE_ACCO VALUES('200045000', 'UG2', 'MG road', '919-666-1002')";
        String query3 = "INSERT INTO PRIVATE_ACCO VALUES('200045212', 'UG3', 'OOLS road', '919-666-1022')";
        String query4 = "INSERT INTO PRIVATE_ACCO VALUES('200045222', 'UG4', 'ADS road', '919-666-1033')";
        String query5 = "INSERT INTO PRIVATE_ACCO VALUES('200045233', 'GDS', 'DBMS road', '919-666-1023')";
        String query6 = "INSERT INTO PRIVATE_ACCO VALUES('200045255', 'PHD', 'SOC road', '919-666-1055')";


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
            String query = "DROP TABLE PRIVATE_ACCO";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException ex){
            System.err.println( " Table  PRIVATE_ACCO : " + ex.getMessage());
        }
    }
}
