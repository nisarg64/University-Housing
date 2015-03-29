package db.table;

import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class InvoiceTable implements Table {
    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE INVOICE ("+
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

    @Override
    public void dropTable(Connection conn) throws SQLException {
        try {
            String query = "DROP TABLE INVOICE";
            DBAccessor.executeQuery(conn, query);

        }catch (SQLException e){
            System.err.println( " Table INVOICE : " + e.getMessage());
        }
    }
}
