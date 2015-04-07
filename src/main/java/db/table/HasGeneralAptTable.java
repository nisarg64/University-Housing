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
public class HasGeneralAptTable extends Table{

    @Override
    public String getTableName() {
        return "GENERAL_APARTMENTS";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " apt_id "+ColumnTypes.ID_TYPE+","+
                " gen_apt_id " + ColumnTypes.ID_TYPE + " ," +
                " num_bedroom number, " +
                " num_bath number, " +
                " PRIMARY KEY (apt_id), " +
                " FOREIGN KEY (gen_apt_id) REFERENCES GENERAL_APT " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('101', '3', 3, 3)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('102', '3', 4, 4)";

        String query3 = "INSERT INTO " + getTableName() + " VALUES('103', '4', 4, 4)";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('104', '4', 3, 3)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
