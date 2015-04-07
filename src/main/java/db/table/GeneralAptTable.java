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
        String query = " CREATE TABLE " + getTableName() +" (" +
                " gen_apt_id " + ColumnTypes.ID_TYPE + " ," +
                " num_room number, " +
                " rent_per_bed number, " +
                " security_deposit number, " +
                " PRIMARY KEY (gen_apt_id), " +
                " FOREIGN KEY (gen_apt_id) REFERENCES HOUSING " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('3', " +
                " 5, " +
                " 350, " +
                " 400 " +
                " )";

        String query2 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('4', " +
                " 3, " +
                " 375, " +
                " 450 " +
                " )";

        queries.add(query1);
        queries.add(query2);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
