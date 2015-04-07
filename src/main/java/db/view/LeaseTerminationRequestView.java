package db.view;

import db.table.LeaseTable;
import db.table.LeaseTerminationRequestTable;
import pojo.Lease;
import pojo.LeaseTerminationRequest;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 06-04-15
 */
public class LeaseTerminationRequestView extends View {

    public static final String VIEW_NAME= "LT_REQUEST_VIEW";
    public static final String TR_STATUS = "tr_status";
    public static final String L_STATUS = "l_status";
    public static final String L_REQUEST_NUMBER = "l_request_number";

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT l.request_number as " + L_REQUEST_NUMBER + ", l.res_id, l.status as " + L_STATUS + ", l.enter_date, l.duration, " +
                "l.payment_option, l.security_deposit, lt.request_number as request_number, lt.leave_date, lt.status as " + TR_STATUS + ", lt.inspection_date, lt.reason" +
                " FROM LEASE l inner join " + LeaseTerminationRequestTable.TABLE_NAME + " lt on lt.request_number = l.request_number";

        DBAccessor.executeQuery(conn, query);
    }

    public LeaseTerminationRequest viewOpenLeaseTerminationRequestForResident(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "' and "
                + TR_STATUS + " = '" + LeaseTable.RequestStatus.Pending.name() + "'";
        List<LeaseTerminationRequest> request = getLeaseTerminationRequests(conn, query);
        return (request.isEmpty()) ? null : request.get(0);
    }

    public List<LeaseTerminationRequest> viewAllLeaseTerminationRequestForResident(Connection conn, String residentId) {
        String query = "SELECT * FROM " + LeaseTerminationRequestTable.TABLE_NAME + " where res_id = '" + residentId + "'";
        return getLeaseTerminationRequests(conn, query);
    }

    public List<LeaseTerminationRequest> viewLeaseTerminationRequests(Connection conn) {
        String query = "SELECT * FROM " + LeaseTerminationRequestTable.TABLE_NAME + " where "
                + LeaseTerminationRequestTable.STATUS + " = '" + LeaseTable.RequestStatus.Pending.name() + "'";
        System.out.println(query);
        return getLeaseTerminationRequests(conn, query);
    }

    private List<LeaseTerminationRequest> getLeaseTerminationRequests(Connection conn, String query) {
        List<LeaseTerminationRequest> requests = new ArrayList<LeaseTerminationRequest>();
        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                int i = 1;
                LeaseTerminationRequest request = new LeaseTerminationRequest();
                request.setRequestNumner(rs.getInt(LeaseTerminationRequestTable.REQUEST_NUMBER));
                request.setStatus(rs.getString(TR_STATUS));
                request.setLeaveDate(rs.getString(LeaseTerminationRequestTable.LEAVE_DATE));
                request.setInspectionDate(rs.getDate(LeaseTerminationRequestTable.INSPECTION_DATE));
                Lease lease = new Lease();
                lease.setLeaseNumber(rs.getInt(L_REQUEST_NUMBER));
                lease.setResidentId(rs.getString(LeaseTable.RES_ID));
                lease.setStatus(rs.getString(L_STATUS));
                lease.setEnterDate(rs.getDate(LeaseTable.ENTER_DATE));
                lease.setDuration(rs.getInt(LeaseTable.DURATION));
                lease.setPaymentOption(rs.getString(LeaseTable.PAYMENT_OPTION));
                lease.setSecurityDeposit(rs.getInt(LeaseTable.SECURITY_DEPOSIT));
                request.setLease(lease);
                requests.add(request);
            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During View Lease Termination Request" + ex.getMessage());
            ex.printStackTrace();
        }
        return requests;
    }
}
