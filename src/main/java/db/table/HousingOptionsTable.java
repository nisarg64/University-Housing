package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.DBAccessor.executeQuery;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class HousingOptionsTable extends Table {

    @Override
    public String getTableName() {
        return "HOUSING_OPTIONS";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ("+
                "housing_id varchar(32), "+
                "housing_name varchar(75), "+
                "housing_address varchar(100), "+
                "PRIMARY KEY (housing_id))";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new ArrayList<String>();

        String query1 = "INSERT INTO " + getTableName() + " VALUES('CH1', 'Alexander Hall','Main Campus Drive')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('CH2', 'Daniels Hall','Sullivan Drive')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('CH3', 'Greek Village','Avent Ferry Road')" ;

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
