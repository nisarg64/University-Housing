package db.table;

import db.view.InvoiceView;
import pojo.Invoice;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Nisarg on 28-Mar-15.
 */
public class InvoiceTable extends Table {
    public static final String INVOICE_SEQUENCE = "INVOICE_SEQUENCE";

    @Override
    public String getTableName() {
        return "INVOICE";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE " + getTableName() + " ( " +
                "invoice_id number, " +
                "resident_id varchar2(10), " +
                "housing_rent INTEGER, " +
                "parking_rent INTEGER, " +
                "lease_no number, " +
                "other_charges float(6), " +
                "late_fees float(6), " +
                "due_date " + ColumnTypes.DATE_TYPE + ", " +
                "deposit_amount float(6), " +
                "payment_status varchar2(10), " +
                "early_termination_fees float(6), " +
                "total_amount Float(10), " +
                "payment_date " + ColumnTypes.DATE_TYPE + " , " +
                "amount_paid float(10), " +
                "payment_method varchar2(20), " +
                "PRIMARY KEY (invoice_id), " +
                " FOREIGN KEY (resident_id) REFERENCES RESIDENT, " +
                " FOREIGN KEY (lease_no) REFERENCES LEASE )";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

    }

    public Invoice getCurrentInvoiceDetails(Connection conn, String username) {
        Invoice invoice = null;

        String query = "SELECT invoice_id, resident_id, housing_rent, parking_rent, " +
                "lease_no, other_charges, late_fees, deposit_amount, due_date, payment_status " +
                " FROM " + getTableName() + " where resident_id = '" + username + "' " +
                "AND invoice_id = (SELECT max(invoice_id) FROM " + getTableName() + " where resident_id ='" + username + "' )";
        System.out.println(query);

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while (resultSet.next()) {
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
                invoice.setTotalAmount(invoice.getHousingRent() + invoice.getParkingRent() + invoice.getLateFees() + invoice.getOtherCharges());

                System.out.println(invoice);

            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }

        return invoice;
    }

    public Invoice insertFutureInvoices(Connection conn, String residentId) {

        InvoiceView invoiceView = new InvoiceView();
        Invoice invoice = invoiceView.getInvoiceDetails(conn, residentId);
        if (invoice == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat();
        invoice.setPaymentDate(null);
        invoice.setPaymentStatus("BILLED");
        Date dueDate = null;
        if (invoice.getPaymentOption().equalsIgnoreCase("monthly")) {

            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 5);
            dueDate = calendar.getTime();
            invoice.setDueDate(sdf.format(dueDate));
            calendar.add(Calendar.DAY_OF_MONTH, -5);
        } else {
            //Semester Option
            calendar.add(Calendar.MONTH, 4);
            calendar.set(Calendar.DAY_OF_MONTH, 5);
            dueDate = calendar.getTime();
            invoice.setDueDate(sdf.format(dueDate));
            calendar.add(Calendar.DAY_OF_MONTH, -5);
            invoice.setHousingRent(invoice.getHousingRent() * 4);
            invoice.setParkingRent(invoice.getParkingRent() * 4);

        }
        invoice.setLateFees(Float.valueOf(0));

        invoice.setOtherCharges(Float.valueOf(0));
        invoice.setEarlyTerminationFees(Float.valueOf(0));
        String query = generateInsertQuery(invoice);
        System.out.println(query);

        /*try{
            DBAccessor.executeQuery(conn, query);
        }catch (SQLException ex){
            ex.printStackTrace();
        }*/
    return invoice;

}

    public List<Invoice> getFormerInvoices(Connection conn, String resident_id) {
        boolean isSingle = false;
        List<Invoice> formerInvoices = null;

        String query1 = "SELECT count(*) FROM "+getTableName()+" WHERE resident_id = '"+ resident_id+"'";
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query1)) {
            while (resultSet.next()) {
                if(resultSet.getInt(1) == 0 )
                    isSingle = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }

        if(isSingle){
            String query = "Select * from " + getTableName() +
                    " where resident_id = '" + resident_id + "'";
            try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
                formerInvoices = new ArrayList<>();
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setInvoiceId(resultSet.getString("invoice_id"));
                    invoice.setTotalAmount(resultSet.getFloat("total_amount"));
                    invoice.setDueDate(resultSet.getString("due_date"));
                    formerInvoices.add(invoice);
                }
                System.out.println(formerInvoices);
            } catch (SQLException ex) {
                System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
            }
            return formerInvoices;
        }


        String query = "Select * from " + getTableName() +
                " where resident_id = '" + resident_id + "' AND due_date NOT IN " +
                "(SELECT max(due_date) FROM " + getTableName() + " where resident_id ='" + resident_id + "' )";
        System.out.println(query);
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            formerInvoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getString("invoice_id"));
                invoice.setTotalAmount(resultSet.getFloat("total_amount"));
                invoice.setDueDate(resultSet.getString("due_date"));
                formerInvoices.add(invoice);
            }
            System.out.println(formerInvoices);
        } catch (SQLException ex) {
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }
        return formerInvoices;
    }


    public void insertFormerInvoices(Connection conn, String residentId) {

        InvoiceView invoiceView = new InvoiceView();
        Invoice invoice = new Invoice();
        invoice = invoiceView.getInvoiceDetails(conn, residentId);
        if (invoice == null)
            return;

        System.out.println(invoice);
        Calendar calendar = Calendar.getInstance();
        Date enterDate = invoice.getEnterDate();
        Date leaveDate = new Date();

        //Date leaveDate = invoice.getLeaveDate();
        System.out.println(enterDate);
        System.out.println(leaveDate);

        calendar.setTimeInMillis(enterDate.getTime());

        List<String> queries = new LinkedList<>();
        while (calendar.getTime().getTime() < leaveDate.getTime()) {

            Date payDate = getPayDate(calendar.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat();
            invoice.setPaymentDate(sdf.format(payDate));
            invoice.setPaymentStatus("PAID");
            Date dueDate = null;
            if (invoice.getPaymentOption().equalsIgnoreCase("monthly")) {
                System.out.println(calendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                dueDate = calendar.getTime();
                invoice.setDueDate(sdf.format(dueDate));
                calendar.add(Calendar.MONTH, 2);
                calendar.add(Calendar.DAY_OF_MONTH, -5);
            } else {
                //Semester Option
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                dueDate = calendar.getTime();
                invoice.setDueDate(sdf.format(dueDate));
                calendar.add(Calendar.MONTH, 4);
                calendar.add(Calendar.DAY_OF_MONTH, -5);
            }
            if (payDate.compareTo(dueDate) > 0)
                invoice.setLateFees(Float.valueOf(50));
            else
                invoice.setLateFees(Float.valueOf(0));

            invoice.setOtherCharges(Float.valueOf(0));
            invoice.setEarlyTerminationFees(Float.valueOf(0));
            invoice.setPaymentMethod("Cheque");
            String query = generateInsertQuery(invoice);
            System.out.println(query);
            queries.add(query);

        }
        if(invoice.getPaymentOption().equalsIgnoreCase("monthly")){
            Date payDate = getPayDate(calendar.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat();
            invoice.setPaymentDate(sdf.format(payDate));
            invoice.setPaymentStatus("BILLED");
            Date dueDate = null;
            System.out.println(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, 5);
            dueDate = calendar.getTime();
            invoice.setDueDate(sdf.format(dueDate));
            calendar.add(Calendar.MONTH, 2);
            invoice.setLateFees(Float.valueOf(0));
            invoice.setOtherCharges(Float.valueOf(0));
            invoice.setEarlyTerminationFees(Float.valueOf(0));
            invoice.setPaymentMethod(null);
            System.out.println(invoice);
            String query = generateInsertQuery(invoice);
            System.out.println(query);
            queries.add(query);
        }

        try{
            DBAccessor.executeBatchQuery(conn, queries);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private String generateInsertQuery(Invoice invoice) {
        String query = "INSERT INTO " + getTableName() + " VALUES ( INVOICE_SEQUENCE.NEXTVAL , " +
                "'" + invoice.getResidentId() + "', "
                + invoice.getHousingRent() + ", "
                + invoice.getParkingRent() + ", "
                + invoice.getLeaseNo() + ", "
                + invoice.getOtherCharges() + ", "
                + invoice.getLateFees() + ",to_date('"
                + invoice.getDueDate() + "', 'MM/DD/YY HH:MI AM'), " +
                +invoice.getDepositAmount() + ",  '"
                + invoice.getPaymentStatus() + "', "
                + invoice.getEarlyTerminationFees() + ", "
                + (invoice.getHousingRent() + invoice.getParkingRent() + invoice.getLateFees() + invoice.getOtherCharges()) + ", to_date('"
                + invoice.getPaymentDate() + "', 'MM/DD/YY HH:MI AM'), " +
                +(invoice.getHousingRent() + invoice.getParkingRent() + invoice.getLateFees() + invoice.getOtherCharges()) + ", "
                + "'" + invoice.getPaymentMethod() + "' " +
                " )";
        return query;
    }

    private Date getPayDate(Date currentDate) {

        Calendar payCal = Calendar.getInstance();
        payCal.setTimeInMillis(currentDate.getTime());
        System.out.println(payCal.getTime());
        payCal.add(Calendar.DAY_OF_MONTH, new Random().nextInt(10));

        return payCal.getTime();

    }

}
