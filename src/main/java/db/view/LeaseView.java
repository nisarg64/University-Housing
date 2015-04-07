package db.view;

import db.table.LeaseTable;
import pojo.Lease;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseView extends View {

    public static final String VIEW_NAME = "LEASE_VIEW";



    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        String query = "CREATE VIEW " + getViewName() + " as " +
                " SELECT l.request_number, l.res_id, l.status, l.enter_date, l.duration, " +
                "l.payment_option, l.security_deposit, l.location_number, l.housing_id" +
                " FROM LEASE l";
        DBAccessor.executeQuery(conn, query);
    }

    public List<Lease> viewFormerLeases(Connection conn, String residentId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(getViewName()).append(" ");
        query.append("where ");
        query.append(LeaseTable.RES_ID).append(" = '").append(residentId).append("'");
        query.append(" and ");
        query.append(LeaseTable.STATUS).append(" = '").append(LeaseTable.RequestStatus.Completed).append("'");

        return getLeases(conn, query.toString());
    }

    public List<Lease> viewAllLeaseRequestsForResident(Connection conn, String residentId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(getViewName()).append(" ");
        query.append("where ");
        query.append(LeaseTable.RES_ID).append(" = '").append(residentId).append("'");

        return getLeases(conn, query.toString());
    }

    public List<Lease> viewOpenLeaseRequests(Connection conn) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseTable.STATUS + " = '" + LeaseTable.RequestStatus.Pending.name() + "'";
        System.out.println(query);
        return getLeases(conn, query);
    }

    public Lease viewCurrentLease(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "' and "
                + LeaseTable.STATUS + " = '" + LeaseTable.RequestStatus.InProgress + "'";
        List<Lease> leases = getLeases(conn, query);
        if (leases.isEmpty()) {
            return null;
        } else {
            return leases.get(0);
        }
    }

    public Lease viewCurrentLeaseRequest(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where res_id = '" + residentId + "' and "
                + LeaseTable.STATUS + " IN ('" + LeaseTable.RequestStatus.Pending + "','" + LeaseTable.RequestStatus.Processed
                + "','" + LeaseTable.RequestStatus.WaitList + "','" + LeaseTable.RequestStatus.InProgress + "')";
        List<Lease> leases = getLeases(conn, query);
        if (leases.isEmpty()) {
            return null;
        } else {
            return leases.get(0);
        }
    }

    public Lease viewLease(Connection conn, int leaseNumber) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseTable.REQUEST_NUMBER + " = " + leaseNumber + "";
        List<Lease> leases = getLeases(conn, query);
        return leases.isEmpty() ? null : leases.get(0);
    }

    private List<Lease> getLeases(Connection conn, String query) {
        List<Lease> leases = new ArrayList<Lease>();
        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                Lease lease = new Lease();
                lease.setLeaseNumber(rs.getInt(LeaseTable.REQUEST_NUMBER));
                lease.setResidentId(rs.getString(LeaseTable.RES_ID));
                lease.setStatus(rs.getString(LeaseTable.STATUS));
                lease.setEnterDate(rs.getDate(LeaseTable.ENTER_DATE));
                lease.setDuration(rs.getInt(LeaseTable.DURATION));
                lease.setPaymentOption(rs.getString(LeaseTable.PAYMENT_OPTION));
                lease.setSecurityDeposit(rs.getInt(LeaseTable.SECURITY_DEPOSIT));
                lease.setLocationNumber(rs.getString(LeaseTable.LOCATION_NUMBER));
                lease.setHousingId(rs.getString(LeaseTable.HOUSING_ID));
                leases.add(lease);
            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During View Lease " + ex.getMessage());
        }
        return leases;
    }


}
