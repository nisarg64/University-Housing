
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
public class HasFamilyAptTable extends Table{

    @Override
    public String getTableName() {
        return "FAMILY_APT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() +" (" +
                " apt_id "+ColumnTypes.ID_TYPE+","+
                " f_apt_id " + ColumnTypes.ID_TYPE + " ," +
                " num_bed number, " +
                " rent number, " +
                " security_deposit number, " +
                " PRIMARY KEY (apt_id), " +
                " FOREIGN KEY (f_apt_id) REFERENCES HOUSING " +
                ")";

        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('105', '5', 1, 450, 500)";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('106', '5', 2, 550, 600)";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('107', '5', 3, 700, 800)";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
