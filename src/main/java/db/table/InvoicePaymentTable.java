package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class InvoicePaymentTable extends Table {
    @Override
    public String getTableName() {
        return "INVOICE_PAYMENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() +" ("+
                        "invoice_payment_id varchar2(20), "+
                        "payment_date timestamp, "+
                        "amount_paid float(10), "+
                        "payment_method varchar2(20), "+
                        "PRIMARY KEY (invoice_payment_id) )";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }


}
