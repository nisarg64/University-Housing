package db;

import db.table.Table;
import db.view.View;
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

    private List<Table> tables = new LinkedList<>();
    private List<View> views = new LinkedList<>();

    private void make(Table table, Connection conn) throws SQLException {

        table.createTable(conn);
        System.out.println("TABLE CREATED SUCCESSFULLY [" +  table.getTableName() + "] ");
        table.insertIntoTable(conn);
        System.out.println("DATA INSERTED SUCCESSFULLY [" +  table.getTableName() + "] ");
        System.out.println("-------------------------------------------------------------");
    }

    private void make(View view, Connection conn) throws SQLException{
        view.createView(conn);
        System.out.println("VIEW CREATED SUCCESSFULLY [" +  view.getViewName() + "] ");
        System.out.println("-------------------------------------------------------------");
    }

    public void addTable(Table table){
        tables.add(table);
    }

    public void addView(View view){
        views.add(view);
    }

    public void makeAll(){

        Connection conn = null;
        try {
            conn = DBAccessor.getConnection();

            for (int i = views.size()-1; i >= 0; --i) {
                views.get(i).dropView(conn);
            }

            for (int i = tables.size()-1; i >= 0; --i) {
                tables.get(i).dropTable(conn);
            }

            for (Table table : tables) {
                make(table, conn);
            }

            for (View view : views) {
                make(view, conn);
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
