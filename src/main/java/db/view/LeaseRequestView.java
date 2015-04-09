package db.view;

import db.table.LeasePreferenceTable;
import db.table.LeaseRequestTable;
import db.table.LeaseTable;
import db.table.ResidentHallTable;
import pojo.LeasePreference;
import pojo.LeaseRequest;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Nikhil
 * Date: 08-04-15
 */
public class LeaseRequestView extends View {

    @Override
    public String getViewName() {
        return LeaseRequestTable.TABLE_NAME;
    }

    @Override
    public void createView(Connection conn) throws SQLException {
        // DO Nothing
    }

    public LeaseRequest viewCurrentLeaseRequest(Connection conn, String residentId) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseRequestTable.RES_ID + " = '" + residentId + "' and "
                + LeaseRequestTable.STATUS + " IN ('" + LeaseTable.RequestStatus.Pending + "','" + LeaseTable.RequestStatus.Processed
                + "','" + LeaseTable.RequestStatus.WaitList + "','" + LeaseTable.RequestStatus.InProgress + "')";
        List<LeaseRequest> leaseRequests = getLeaseRequests(conn, query);
        if (leaseRequests.isEmpty()) {
            return null;
        } else {
            return leaseRequests.get(0);
        }
    }

    public List<LeaseRequest> viewOpenLeaseRequests(Connection conn) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseRequestTable.STATUS + " IN ('"
                + LeaseTable.RequestStatus.Pending.name() + "', '" + LeaseTable.RequestStatus.WaitList.name() +"')";
        System.out.println(query);
        return getLeaseRequests(conn, query);
    }

    public LeaseRequest viewLeaseRequest(Connection conn, int requestNumber) {
        String query = "SELECT * FROM " + getViewName() + " where " + LeaseRequestTable.REQUEST_NUMBER + " = " + requestNumber;
        System.out.println(query);
        List<LeaseRequest> requests = getLeaseRequests(conn, query);
        if (requests.isEmpty()) {
            return null;
        } else {
            LeaseRequest request = requests.get(0);
            populatePreferences(conn, request);
            return request;
        }
    }

    public List<LeaseRequest> viewAllLeaseRequestsForResident(Connection conn, String residentId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(getViewName()).append(" ");
        query.append("where ");
        query.append(LeaseRequestTable.RES_ID).append(" = '").append(residentId).append("'");

        return getLeaseRequests(conn, query.toString());
    }

    private List<LeaseRequest> getLeaseRequests(Connection conn, String query) {
        List<LeaseRequest> leaseRequests = new ArrayList<LeaseRequest>();
        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                LeaseRequest leaseRequest = new LeaseRequest();
                leaseRequest.setRequestNumber(rs.getInt(LeaseRequestTable.REQUEST_NUMBER));
                leaseRequest.setResidentId(rs.getString(LeaseRequestTable.RES_ID));
                leaseRequest.setStatus(rs.getString(LeaseRequestTable.STATUS));
                leaseRequest.setEnterDate(rs.getDate(LeaseRequestTable.ENTER_DATE));
                leaseRequest.setDuration(rs.getInt(LeaseRequestTable.DURATION));
                leaseRequest.setPaymentOption(rs.getString(LeaseRequestTable.PAYMENT_OPTION));
                leaseRequest.setUsePrivateAccommodation(rs.getBoolean(LeaseRequestTable.USE_PRIVATE_ACCOMMODATION));
                leaseRequest.setUpdatedBy(rs.getString(LeaseRequestTable.UPDATED_BY));
                leaseRequest.setUpdatedOn(rs.getDate(LeaseRequestTable.UPDATED_ON));
                leaseRequests.add(leaseRequest);
            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During Get Lease Requests" + ex.getMessage());
            ex.printStackTrace();
        }
        return leaseRequests;
    }

    public void populatePreferences(Connection conn, LeaseRequest leaseRequest) {
        String query = "select p.*, h.name from " + LeasePreferenceTable.TABLE_NAME +
                " p left outer join housing h on h.housing_id = p.hall_id where "
                + LeaseRequestTable.REQUEST_NUMBER + " = " + leaseRequest.getRequestNumber();

        try (ResultSet rs = DBAccessor.selectQuery(conn, query)) {
            while (rs.next()) {
                LeasePreference leasePreference = new LeasePreference();
                leasePreference.setSequenceNumber(rs.getInt(LeasePreferenceTable.SEQUENCE_NUMBER));
                leasePreference.setType(rs.getString(LeasePreferenceTable.TYPE));
                leasePreference.setHallId(rs.getString(ResidentHallTable.HALL_ID));
                leasePreference.setRequestNumber(leaseRequest.getRequestNumber());
                leasePreference.setHallName(rs.getString("name"));
                if (leasePreference.getSequenceNumber() == 3) {
                    leaseRequest.setPreference3(leasePreference);
                } else if (leasePreference.getSequenceNumber() == 2) {
                    leaseRequest.setPreference2(leasePreference);
                } else {
                    leaseRequest.setPreference1(leasePreference);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error Occurred During Populate Preferences " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
