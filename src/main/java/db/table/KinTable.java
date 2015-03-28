package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

import static util.DBAccessor.executeQuery;

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
                " name VARCHAR(32), " +
                " relation VARCHAR(12), " +
                " address_street VARCHAR(100), " +
                " address_city VARCHAR(32), " +
                " address_postcode VARCHAR(10), " +
                " contact_no VARCHAR(12), " +
                " student_id CHAR(10), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

}
