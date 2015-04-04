package db.table;

import pojo.Invoice;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                        "FOREIGN KEY (invoice_payment_id) REFERENCES INVOICE_PAYMENT (invoice_payment_id) )";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public Invoice getCurrentInvoiceDetails(Connection conn, String username) {

        String query = "SELECT invoice_id, resident_id, housing_rent, parking_rent, " +
                "lease_no, pending_charges, late_fees, deposit_amount, due_date, payment_status, " +
                "invoice_payment_id" +
                " FROM "+ getTableName()+" where resident_id = '"+username+"' " +
                "AND invoice_id = (SELECT max(invoice_id) FROM "+getTableName()+" where resident_id ='"+username+"' )";

        Invoice invoice = null;
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getString("invoice_id"));
                invoice.setResidentId(resultSet.getString("resident_id"));
                invoice.setHousingRent(resultSet.getInt("housing_rent"));
                invoice.setParkingRent(resultSet.getInt("parking_rent"));
                invoice.setLeaseNo(resultSet.getString("lease_no"));
                invoice.setPendingCharges(resultSet.getFloat("pending_charges"));
                invoice.setLateFees(resultSet.getFloat("late_fees"));
                invoice.setDepositAmount(resultSet.getFloat("deposit_amount"));
                invoice.setDueDate(resultSet.getTimestamp("due_date"));
                invoice.setPaymentStatus(resultSet.getString("payment_status"));
                invoice.setInvoicePaymentId(resultSet.getString("invoice_payment_id"));


                System.out.println(invoice);

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }
        return invoice;
    }

    public List<String> getFormerInvoices(Connection conn, String resident_id) {
        List<String> formerInvoices = null;
        String query = "Select invoice_id from '"+getTableName()+
                "' where resident_id = '"+resident_id+"' AND invoice_id NOT IN "+
                "(SELECT max(invoice_id) FROM "+getTableName()+" where resident_id ='"+resident_id+"' )";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            formerInvoices = new ArrayList<String>();
            while(resultSet.next()){
                formerInvoices.add(resultSet.getString("invoice_id"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }
        return formerInvoices;
    }


}
