package db.view;

import db.table.LeaseTable;
import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseView extends View {

    @Override
    public String getViewName() {
        return "LEASE_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT l.lease_number, l.res_id, l.status, l.enter_date, l.duration, l.leave_date, " +
                "l.payment_option, l.security_deposit, l.cutoff_date" +
                " FROM LEASE l";
        DBAccessor.executeQuery(conn, query);
    }

    public Lease viewCurrentLease(Connection conn, String residentId) {
        Lease lease = null;
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "'";

        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            if(rs.next()){
                lease = new Lease();
                lease.setLeaseNumber(rs.getInt(LeaseTable.LEASE_NUMBER));
                lease.setResidentId(rs.getString(LeaseTable.RES_ID));
                lease.setStatus(rs.getString(LeaseTable.STATUS));
                lease.setEnterDate(rs.getDate(LeaseTable.ENTER_DATE));
                lease.setDuration(rs.getInt(LeaseTable.DURATION));
                lease.setLeaveDate(rs.getDate(LeaseTable.LEAVE_DATE));
                lease.setPaymentOption(rs.getString(LeaseTable.PAYMMENT_OPTION));
                lease.setSecurityDeposit(rs.getInt(LeaseTable.SECURITY_DEPOSIT));
                lease.setCutoffDate(rs.getDate(LeaseTable.CUTOFF_DATE));
                System.out.println(lease);
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During View Lease " + ex.getMessage());
        }

        return lease;
    }

}
