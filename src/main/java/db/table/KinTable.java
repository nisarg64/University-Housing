package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class KinTable extends Table {

    @Override
    public String getTableName() {
        return "KIN";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {

        String query = " CREATE TABLE "+ getTableName() + " (" +
                " name " +   ColumnTypes.VARCHAR2_SIZE_50_TYPE + " NOT NULL ," +
                " relation " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " NOT NULL ," +
                " address_street " +   ColumnTypes.VARCHAR2_SIZE_200_TYPE + " ," +
                " address_city " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " address_postcode " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " address_country " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " contact_no " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " res_id " +   ColumnTypes.ID_TYPE + " ," +
                " PRIMARY KEY (name, res_id), " +
                " FOREIGN KEY (res_id) REFERENCES RESIDENT " +
                " ON DELETE CASCADE " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('Sirius Black', 'Godfather', '12, Grimmauld Place', '','',''," +
                " '9439560752','100540001' )";

        String query2 = "INSERT INTO " + getTableName() + " VALUES('Lucius Malfoy', 'Father', '31 B, Malfoy Drive', 'New York','27506','USA'," +
                " '9439560752','100540002' )";

        String query3 = "INSERT INTO " + getTableName() + " VALUES('Aurthur Weasley', 'Father', '31 B, Weasley Road', 'Rome','27456','Italy'," +
                " '9436660752','100540003' )";

        String query4 = "INSERT INTO " + getTableName() + " VALUES('Mr. Granger', 'Father', '32 A, Granger Road', 'Edinburg','25556','Scotland'," +
                " '9433360752','100540004' )";

        String query5 = "INSERT INTO " + getTableName() + " VALUES('Aurthur Weasley', 'Father', '31 B, Weasley Road', 'Rome','27456','Italy'," +
                " '9436660752','100540005' )";

        String query6 = "INSERT INTO " + getTableName() + " VALUES('Aurthur Weasley', 'Father', '31 B, Weasley Road', 'Rome','27456','Italy'," +
                " '9436660752','100540006' )";

        String query7 = "INSERT INTO " + getTableName() + " VALUES('Aurthur Weasley', 'Father', '31 B, Weasley Road', 'Rome','27456','Italy'," +
                " '9436660752','100540007' )";

        String query8 = "INSERT INTO " + getTableName() + " VALUES('Igor Karkaroff', 'Headmaster', '32 A, Krum Road', 'Sofia','2221','Bulgaria'," +
                " '94363530752','200540001' )";

        String query9 = "INSERT INTO " + getTableName() + " VALUES('Bathilda Bagshot', 'Friend', '32 A, Bagshot Road', 'Zurich','35221','Switzerland'," +
                " '94363595752','200540002' )";

        String query10 = "INSERT INTO " + getTableName() + " VALUES('Petunia Dursley', 'Wife', '32 A, Dursley Road', 'Raleigh','27606','USA'," +
                " '94363654752','100540008' )";


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

}
