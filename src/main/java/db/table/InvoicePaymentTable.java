package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class InvoicePaymentTable extends Table {
    public static final String INVOICE_PAYMENT_SEQUENCE = "INVOICE_PAYMENT_SEQUENCE";

    @Override
    public String getTableName() {
        return "INVOICE_PAYMENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() +" ("+
                        "invoice_payment_id number, "+
                        "payment_date timestamp, "+
                        "amount_paid float(10), "+
                        "payment_method varchar2(20), "+
                        "invoice_id number, "+
                        "PRIMARY KEY (invoice_payment_id) " +
                "FOREIGN KEY (invoice_id) REFERENCES INVOICE_PAYMENT, " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName()
                + " VALUES(" +
                INVOICE_PAYMENT_SEQUENCE + ".NEXTVAL" +
                ", CURRENT_TIMESTAMP," +
                " 500," +
                " 'Cash'" +
                " 1001" +
                ")"; // HID1_O1 from Room table.

        queries.add(query1);
//        queries.add(query2);
        DBAccessor.executeBatchQuery(conn, queries);
    }


}
