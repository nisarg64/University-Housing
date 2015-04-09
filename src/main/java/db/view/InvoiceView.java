package db.view;

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
                invoice.setLeaseNo(resultSet.getInt("lease_no"));
                invoice.setOtherCharges(resultSet.getFloat("other_charges"));
                invoice.setLateFees(resultSet.getFloat("late_fees"));
                invoice.setDepositAmount(resultSet.getFloat("deposit_amount"));
                invoice.setDueDate(resultSet.getString("due_date"));
                invoice.setPaymentStatus(resultSet.getString("payment_status"));
                invoice.setTotalAmount(resultSet.getFloat("total_amount"));
                invoice.setPaymentDate(resultSet.getString("payment_date"));
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
        Invoice invoice = new Invoice();
        String housingId = null;
        String location_no = null;
        String housingType = null;
        invoice.setHousingRent(Integer.valueOf(0));
        invoice.setParkingRent(Integer.valueOf(0));
        invoice.setOtherCharges(Float.valueOf(0));
        invoice.setLateFees(Float.valueOf(0));

        LeaseView leaseView = new LeaseView();
        Lease lease = leaseView.viewCurrentLease(conn, residentId);
        if(lease == null)
            return null;
        housingId = lease.getHousingId();
        location_no = lease.getLocationNumber();
        invoice.setResidentId(residentId);
        invoice.setLeaseNo(lease.getLeaseNumber());
        invoice.setEnterDate(lease.getStartDate());
        System.out.println(invoice.getEnterDate());
        invoice.setLeaveDate(lease.getLeaveDate());
        invoice.setPaymentOption(lease.getPaymentOption());

        String query = "SELECT TYPE FROM HOUSING WHERE HOUSING_ID = '" +housingId+"'";
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while (resultSet.next()) {
                housingType = resultSet.getString("TYPE");
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Invoice View " + ex.getMessage());
        }
        if("Residence Halls".equals(housingType) || "General Student Apartments".equals(housingType)){
            query = "SELECT MONTHLY_RENT as RENT, SECURITY_DEPOSIT as DEPOSIT FROM ROOMS WHERE PARENT_ID = '"+housingId.trim()+"' AND PLACE_NUM = '"+location_no+"'";
        }else if("Family Apartments".equals(housingType)){
            query = "SELECT RENT as RENT,SECURITY_DEPOSIT as DEPOSIT FROM FAMILY_APT WHERE F_APT_ID = '"+housingId+"' AND APT_ID = '"+location_no+"'";
        }

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
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
