package db.table;



import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : Anand
 * Created on 4/1/15.
 */
public class TicketSeverityTable extends Table{

    @Override
    public String getTableName() {
        return "TICKET_SEVERITY";
    }

//    ORDER BY CASE ticket_priority_id
//    WHEN 'low' THEN 1
//    WHEN 'med' THEN 2
//    WHEN 'high' THEN 3 END

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " ticket_type VARCHAR(20), " +
                " SEVERITY VARCHAR(20), " +
                " PRIMARY KEY (ticket_type) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('Water', 'High')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('Electricity', 'High')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('Appliances', 'Medium')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('Internet', 'Medium')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('Cleaning', 'Low')";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('Miscellaneous', 'Low')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        DBAccessor.executeBatchQuery(conn, queries);
    }
}