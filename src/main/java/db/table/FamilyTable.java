package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static util.DBAccessor.executeQuery;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class FamilyTable extends Table {

    @Override
    public String getTableName() {
        return "FAMILY";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + "( " +
                " name " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                " dob " + ColumnTypes.DATE_TYPE + " ," +
                " res_id " + ColumnTypes.ID_TYPE + " ," +
<<<<<<< Updated upstream
                " PRIMARY KEY (name, res_id), " +
=======
>>>>>>> Stashed changes
                " FOREIGN KEY (res_id) REFERENCES RESIDENT " +
                " ON DELETE CASCADE " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES('Fleur Delacour', '31-Mar-1984', '100540007')";
        String query2 = "INSERT INTO " + getTableName() + " VALUES('LOLA Amiga', '31-Mar-1984', '100540007')";
        String query3 = "INSERT INTO " + getTableName() + " VALUES('Gracias', '31-Mar-1984', '100540007')";
        String query4 = "INSERT INTO " + getTableName() + " VALUES('Khali', '31-Mar-1984', '100540007')";
        String query5 = "INSERT INTO " + getTableName() + " VALUES('Petunia Dursley', '13-Dec-1978', '100540008')";
        String query6 = "INSERT INTO " + getTableName() + " VALUES('Dudley Dursley', '26-Jun-2000', '100540008')";
        String query7 = "INSERT INTO " + getTableName() + " VALUES('Kin Kin', '26-Jun-2005', '100540008')";
        String query8 = "INSERT INTO " + getTableName() + " VALUES('Nakli', '26-Jun-2005', '100540001')";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);

        DBAccessor.executeBatchQuery(conn, queries);
    }
}
