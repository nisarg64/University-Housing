package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class InvoiceTable extends Table {
    @Override
    public String getTableName() {
        return "INVOICE";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ( "+
                        "invoice_id varchar2(32), "+
                        "resident_id varchar2(10), "+
                        "housing_rent INTEGER, "+
                        "parking_rent INTEGER, "+
                        "lease_no varchar2(32), "+
                        "pending_charges float(6), "+
                        "late_fees float(6), "+
                        "due_date timestamp, "+
                        "deposit_amount float(6), "+
                        "payment_status varchar2(10), "+
                        "invoice_payment_id varchar(32), "+
                        "PRIMARY KEY (invoice_id), "+
                        "FOREIGN KEY (invoice_payment_id) REFERENCES INVOICEPAYMENT (invoice_payment_id) )";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

}
