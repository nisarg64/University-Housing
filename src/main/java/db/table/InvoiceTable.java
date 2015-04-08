package db.table;

import db.view.LeaseView;
import pojo.Invoice;
import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "CREATE TABLE " + getTableName() + " ( "+
                        "invoice_id number, "+
                        "resident_id char(10), "+
                        "housing_rent INTEGER, "+
                        "parking_rent INTEGER, "+
                        "lease_no number, "+
                        "other_charges float(6), "+
                        "late_fees float(6), "+
                        "due_date timestamp, "+
                        "deposit_amount float(6), "+
                        "payment_status varchar2(10), "+
                        "early_termination_fees float(6), "+
                        "total_amount Float(6), "+
                        "payment_date timestamp, "+
                        "amount_paid float(10), "+
                        "payment_method varchar2(20), "+
                        "PRIMARY KEY (invoice_id), "+
                        " FOREIGN KEY (resident_id) REFERENCES RESIDENT, " +
                        " FOREIGN KEY (lease_no) REFERENCES LEASE )";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
      /*  List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName()
                + " VALUES(" +
                INVOICE_SEQUENCE + ".NEXTVAL" +
                ", '100540001'," +
                " 500," +
                " 40," +
                " 1," +
                " 1," +
                "1," +
                " CURRENT_TIMESTAMP + 20," +
                " 100," +
                " 'Pending'," +
                " NULL," +
                " NULL," +
                " NULL" +
                ")";

        String query2 = "INSERT INTO " + getTableName()
                + " VALUES(" +
                INVOICE_SEQUENCE + ".NEXTVAL" +
                ", '100540001'," +
                " 500," +
                " 40," +
                " 1," +
                " 1," +
                "1," +
                " CURRENT_TIMESTAMP - 45," +
                " 100," +
                " 'Payed'," +
                " CURRENT_TIMESTAMP," +
                " 450," +
                " 'Cash'" +
                ")";

        String query3 = "INSERT INTO " + getTableName()
                + " VALUES(" +
                INVOICE_SEQUENCE + ".NEXTVAL" +
                ", '200540002 '," +
                " 900," +
                " 85," +
                " 1," +
                " 1," +
                "1," +
                " CURRENT_TIMESTAMP - 5," +
                " 100," +
                " 'Payed'," +
                " CURRENT_TIMESTAMP-2," +
                " 450," +
                " 'Check'" +
                ")";

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        DBAccessor.executeBatchQuery(conn, queries);*/
    }

    public Invoice getCurrentInvoiceDetails(Connection conn, String username) {

        String query = "SELECT invoice_id, resident_id, housing_rent, parking_rent, " +
                "lease_no, pending_charges, late_fees, deposit_amount, due_date, payment_status " +
                " FROM "+ getTableName()+" where resident_id = '"+username+"' " +
                "AND invoice_id = (SELECT max(invoice_id) FROM "+getTableName()+" where resident_id ='"+username+"' )";
        System.out.println(query);
        Invoice invoice = null;
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getString("invoice_id"));
                invoice.setResidentId(resultSet.getString("resident_id"));
                invoice.setHousingRent(resultSet.getInt("housing_rent"));
                invoice.setParkingRent(resultSet.getInt("parking_rent"));
                invoice.setLeaseNo(resultSet.getString("lease_no"));
                invoice.setOtherCharges(resultSet.getFloat("other_charges"));
                invoice.setLateFees(resultSet.getFloat("late_fees"));
                invoice.setDepositAmount(resultSet.getFloat("deposit_amount"));
                invoice.setDueDate(resultSet.getTimestamp("due_date"));
                invoice.setPaymentStatus(resultSet.getString("payment_status"));
//                invoice.setInvoicePaymentId(resultSet.getString("invoice_payment_id"));


                System.out.println(invoice);

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }
        return invoice;
    }

    public List<Invoice> getFormerInvoices(Connection conn, String resident_id) {
        List<Invoice> formerInvoices = null;
        String query = "Select * from "+getTableName()+
                " where resident_id = '"+resident_id+"' AND invoice_id NOT IN "+
                "(SELECT max(invoice_id) FROM "+getTableName()+" where resident_id ='"+resident_id+"' )";
        System.out.println(query);
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            formerInvoices = new ArrayList<>();
            while(resultSet.next()){
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getString("invoice_id"));
                invoice.setTotalAmount(resultSet.getFloat("total_amount"));
                invoice.setDueDate(resultSet.getTimestamp("due_date"));
                formerInvoices.add(invoice);
            }
            System.out.println(formerInvoices);
        }catch (SQLException ex){
            System.err.println("Error Occurred During view invoice information query  " + ex.getMessage());
        }
        return formerInvoices;
    }

    public void insertFormerInvoices(Connection conn, String residentId){

        LeaseView leaseView = new LeaseView();
        Lease lease = leaseView.viewCurrentLease(conn, residentId);
        Date enterDate = lease.getEnterDate();
        Date leaveDate = lease.getLeaveDate();
        Date cutoffDate = lease.getCutoffDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(enterDate.getTime());

        List<String> queries = new LinkedList<>();
        while (calendar.getTime().getTime() < leaveDate.getTime()){

            Date payDate = getPayDate(calendar.getTime());
            Date dueDate = null;
            if(lease.getPaymentOption().equalsIgnoreCase("monthly")){

                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                dueDate = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -5);
            }else {
                //Semester Option
                calendar.add(Calendar.MONTH, 4);
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                dueDate = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -5);
            }
            String query = generateInsertQuery(lease, payDate, dueDate);
            queries.add(query);

        }

        try{
            DBAccessor.executeBatchQuery(conn, queries);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    private String generateInsertQuery(Lease lease, Date payDate, Date dueDate) {
        String query = "INSERT INTO " + getTableName()  + " VALUES ( " + INVOICE_SEQUENCE + ".NEXTVAL , " +
                "'" + lease.getResidentId() + "', "
                + lease.getHousingRent() + ", "
                + lease.getParkingRent() + ", "
                + lease.getLeaseNumber() + ", "
                + lease.getPendingCharge() + ", "
                + dueDate + ", " +
                + lease.getSecurityDeposit() + ",  "
                + "PAID" + ", "
                + payDate + ", " +
                + (lease.getHousingRent() + lease.getParkingRent() + lease.getLateFees()) + ", "
                + "' " + lease.getPaymentMode() +"' " +
                " )";
        return query;
    }

    private Date getPayDate(Date currentDate){

        Calendar payCal = Calendar.getInstance();
        payCal.setTimeInMillis(currentDate.getTime());
        payCal.add(Calendar.DAY_OF_MONTH, new Random(15).nextInt());

        return payCal.getTime();

    }

}
