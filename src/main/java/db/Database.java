package db;

import db.table.Table;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/26/15.
 */
public class Database {

    private List<Table> tables = new LinkedList<Table>();

    private void make(Table table, Connection conn) throws SQLException {

        table.createTable(conn);
        table.insertIntoTable(conn);
    }

    public void addTable(Table table){
        tables.add(table);
    }

    public void makeAll(){

        Connection conn = null;
        try {
            conn = DBAccessor.getConnection();

            for (int i = tables.size()-1; i >= 0; --i) {
                tables.get(i).dropTable(conn);
            }

            for (Table table : tables) {
                make(table, conn);
            }
            conn.commit();

        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
