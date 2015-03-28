package db.table;

import java.sql.Connection;
import java.sql.SQLException;

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

    }
}
