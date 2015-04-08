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
                " apt_id " + ColumnTypes.ID_TYPE + " ," +
                " num_bedroom number, " +
                " num_bath number, " +
                " PRIMARY KEY (gen_apt_id, apt_id), " +
                " FOREIGN KEY (gen_apt_id) REFERENCES HOUSING " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('3', '101', " +
                " 3, 3 " +
                " )";
        String query2 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('3', '102', " +
                " 4, 4 " +
                " )";

        String query3 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('4', '103', " +
                " 3, 3 " +
                " )";
        String query4 = "INSERT INTO " + getTableName() +" " +
                "VALUES" +
                "('4', '104', " +
                " 4, 4 " +
                " )";

        queries.add(query1);
        queries.add(query2);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
