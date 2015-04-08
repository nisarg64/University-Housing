package db.view;

import db.table.LeaseTable;
import pojo.Invoice;
import pojo.Lease;
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

    public Invoice getInvoiceDetails(Connection conn, String residentId) {
        Invoice invoice = null;
        String housingId = null;
        String housingType = null;

        String query = "SELECT HOUSING_ID, TYPE FROM HOUSING WHERE HOUSING_ID = " +
                "(SELECT HOUSING_ID FROM LEASE WHERE res_id = "+residentId.trim()
                +" AND STATUS = '"+ LeaseTable.RequestStatus.InProgress+"')";
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while (resultSet.next()) {
                housingId = resultSet.getString("HOUSING_ID");
                housingType = resultSet.getString("TYPE");
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Invoice View " + ex.getMessage());
        }
        if("Residence Halls".equals(housingType)){
            query = "SELECT MONTHLY_RENT as RENT, SECURITY_DEPOSIT as DEPOSIT FROM RESIDENT_HALL WHERE HALL_ID = '"+housingId.trim()+"'";
        }else if("General Student Apartments".equals(housingType)){
            query = "SELECT RENT_PER_BED as RENT, SECURITY_DEPOSIT as DEPOSIT FROM GENERAL_APT WHERE GEN_APT_ID = '"+housingId+"'";
        }else if("Family Apartments".equals(housingType)){
            query = "SELECT RENT as RENT,SECURITY_DEPOSIT as DEPOSIT FROM FAMILY_APT WHERE F_APT_ID = '"+housingId+"'";
        }

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                invoice = new Invoice();
                invoice.setResidentId(residentId);
                invoice.setHousingRent(resultSet.getInt("RENT"));
                invoice.setDepositAmount(resultSet.getFloat("DEPOSIT"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Former Invoice View " + ex.getMessage());
        }

        query = "SELECT RENTAL_FEE FROM PARKING_SPOT WHERE SPOT_ID = " +
                "(SELECT SPOT_ID FROM PARKING_PERMIT WHERE PERMIT_ID = " +
                "(SELECT PERMIT_ID FROM PARKING_REQUEST WHERE RESIDENT_ID = "+residentId.trim()+"))";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                invoice.setParkingRent(resultSet.getInt("RENTAL_FEE"));
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Former Invoice View " + ex.getMessage());
        }

        return invoice;
    }
}
