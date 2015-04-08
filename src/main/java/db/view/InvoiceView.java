package db.view;

import pojo.Invoice;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nisarg on 4/3/15.
 */
public class InvoiceView extends View{



    @Override
    public String getViewName() {
        return "INVOICE_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {

        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT * FROM INVOICE I " ;

        DBAccessor.executeQuery(conn, query);
    }

    public Invoice getFormerInvoiceDetails(Connection conn, String username, String invoiceId) {

        Invoice invoice = null;
        String query = "SELECT * FROM " + getViewName() + " where resident_id = '" + username + "' AND invoice_id = " + invoiceId;
        System.out.println(query);
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getString("invoice_id"));
                invoice.setResidentId(resultSet.getString("resident_id"));
                invoice.setHousingRent(resultSet.getInt("housing_rent"));
                invoice.setParkingRent(resultSet.getInt("parking_rent"));
                invoice.setLeaseNo(resultSet.getString("lease_no"));
                invoice.setOtherCharges(resultSet.getFloat("pending_charges"));
                invoice.setLateFees(resultSet.getFloat("late_fees"));
                invoice.setDepositAmount(resultSet.getFloat("deposit_amount"));
                invoice.setDueDate(resultSet.getTimestamp("due_date"));
                invoice.setPaymentStatus(resultSet.getString("payment_status"));
                invoice.setTotalAmount(resultSet.getFloat("total_amount"));
                invoice.setPaymentDate(resultSet.getTimestamp("payment_date"));
                invoice.setAmountPaid(resultSet.getFloat("amount_paid"));
                invoice.setPaymentMethod(resultSet.getString("payment_method"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Former Invoice View " + ex.getMessage());
        }

        System.out.println(invoice);
        return invoice;
    }
}
