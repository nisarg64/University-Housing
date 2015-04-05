package db;

import db.table.LeaseTable;
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

            dropSequences(conn);
            createSequences(conn);

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

    private void dropSequences(Connection conn){
        try{
            List<String> queries = new LinkedList<>();
            
            String query = "DROP SEQUENCE pr_sequence";
            String query1 = "DROP SEQUENCE ticket_sequence";
            String query2 = "DROP SEQUENCE permit_sequence";

            queries.add(query);
            queries.add(query1);
            queries.add(query2);

            queries.add("DROP SEQUENCE pr_sequence");
            queries.add("DROP SEQUENCE ticket_sequence");
            queries.add("DROP SEQUENCE " + LeaseTable.LEASE_SEQUENCE);


            DBAccessor.executeBatchQuery(conn, queries);
            System.out.println("DB SEQUENCES DROPPED SUCCESSFULLY ");
            System.out.println("-------------------------------------------------------------");

        }catch (SQLException ex){
            System.err.println("SEQUENCE NOT PRESENT " + ex.getMessage());
        }

    }

    private void createSequences(Connection conn) throws SQLException{
        List<String> queries = new LinkedList<>();


        String query = "CREATE SEQUENCE pr_sequence START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE";
        String query1 = "CREATE SEQUENCE ticket_sequence START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE";
        String query2 = "CREATE SEQUENCE permit_sequence START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE";

        queries.add(query);
        queries.add(query1);
        queries.add(query2);
        queries.add("CREATE SEQUENCE pr_sequence START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE");
        queries.add("CREATE SEQUENCE ticket_sequence START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE");
        queries.add("CREATE SEQUENCE " + LeaseTable.LEASE_SEQUENCE + " START WITH 1000 INCREMENT BY 1 NOCACHE NOCYCLE");


        DBAccessor.executeBatchQuery(conn, queries);
        System.out.println("DB SEQUENCES CREATED SUCCESSFULLY ");
        System.out.println("-------------------------------------------------------------");
    }

}
