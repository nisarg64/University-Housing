package db.view;

import db.table.LeaseRequestTable;
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
        /*LeaseRequestTable.REQUEST_NUMBER + " " + ColumnTypes.ID_INT_TYPE + "," +
                LeaseTable.LEASE_NUMBER + " " + ColumnTypes.ID_INT_TYPE + " not null," +
                LEAVE_DATE + " " + ColumnTypes.DATE_TYPE + " not null," +
                STATUS + " " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " not null," +
                INSPECTION_DATE + " " + ColumnTypes.DATE_TYPE + " ," +
                REASON + " " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " not null," +
                LeaseRequestTable.UPDATED_BY + " " + ColumnTypes.ID_TYPE + "," +
                LeaseRequestTable.UPDATED_ON + " " + ColumnTypes.DATE_TYPE + " ," +*/

        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT lt." + LeaseRequestTable.REQUEST_NUMBER + " as  lt_request, lt." + LeaseTerminationRequestTable.LEAVE_DATE
                + ", lt." + LeaseTerminationRequestTable.STATUS + " as  lt_status, lt." + LeaseTerminationRequestTable.INSPECTION_DATE
                + ", lt." + LeaseRequestTable.UPDATED_BY + " as  lt_updated_by, lt." + LeaseRequestTable.UPDATED_ON + " as  lt_updated_on, l.* "
                + " FROM " + LeaseTerminationRequestTable.TABLE_NAME + " lt" + " inner join " + LeaseView.VIEW_NAME
                + " l on l." + LeaseTable.LEASE_NUMBER + " = lt." + LeaseTable.LEASE_NUMBER;

        DBAccessor.executeQuery(conn, query);
    }

    public LeaseTerminationRequest viewLeaseTerminationRequest(Connection conn, int requestNumber) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseRequestTable.REQUEST_NUMBER + " = " + requestNumber + "";
        List<LeaseTerminationRequest> requests = getLeaseTerminationRequests(conn, query);
        return requests.isEmpty() ? null : requests.get(0);
    }

    public LeaseTerminationRequest viewOpenLeaseTerminationRequestForResident(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "' and "
                + TR_STATUS + " = '" + LeaseTable.RequestStatus.Pending.name() + "'";
        List<LeaseTerminationRequest> request = getLeaseTerminationRequests(conn, query);
        return (request.isEmpty()) ? null : request.get(0);
    }

    public List<LeaseTerminationRequest> viewAllLeaseTerminationRequestForResident(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "'";
        return getLeaseTerminationRequests(conn, query);
    }

    public List<LeaseTerminationRequest> viewLeaseTerminationRequests(Connection conn) {
        String query = "SELECT * FROM " + getViewName() + " where "
                + TR_STATUS + " = '" + LeaseTable.RequestStatus.Pending.name() + "'";
        System.out.println(query);
        return getLeaseTerminationRequests(conn, query);
    }

    private List<LeaseTerminationRequest> getLeaseTerminationRequests(Connection conn, String query) {
        List<LeaseTerminationRequest> requests = new ArrayList<LeaseTerminationRequest>();
        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                int i = 1;
                LeaseTerminationRequest request = new LeaseTerminationRequest();
                request.setRequestNumber(rs.getInt(LeaseRequestTable.REQUEST_NUMBER));
                request.setStatus(rs.getString(TR_STATUS));
                request.setLeaveDate(rs.getDate(LeaseTerminationRequestTable.LEAVE_DATE));
                request.setInspectionDate(rs.getDate(LeaseTerminationRequestTable.INSPECTION_DATE));
                Lease lease = new Lease();
                lease.setLeaseNumber(rs.getInt(L_REQUEST_NUMBER));
                // lease.setResidentId(rs.getString(LeaseTable.RES_ID));
                lease.setStatus(rs.getString(L_STATUS));
                lease.setStartDate(rs.getDate(LeaseTable.START_DATE));
                // lease.setDuration(rs.getInt(LeaseTable.DURATION));
                // lease.setPaymentOption(rs.getString(LeaseTable.PAYMENT_OPTION));
                // lease.setSecurityDeposit(rs.getInt(LeaseTable.SECURITY_DEPOSIT));
                lease.setLocationNumber(rs.getString(LeaseTable.LOCATION_NUMBER));
                lease.setHousingId(rs.getString(LeaseTable.HOUSING_ID));;
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
