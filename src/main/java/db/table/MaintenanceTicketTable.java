package db.table;



import db.view.StaffNameView;
import pojo.TicketRequest;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static util.DBAccessor.executeQuery;

/**
 * Author : Anand
 * Created on 4/1/15.
 */
public class MaintenanceTicketTable extends Table{

    @Override
    public String getTableName() {
        return "MAINTENANCE_TICKET";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " ticket_no NUMBER, " +
                " ticket_type VARCHAR(20), " +
                " ticket_date timestamp, " +
                " res_id varchar2(10), " +
                " location_id VARCHAR(20), " + // room_id or family_apt_id
                " location_type VARCHAR(20), " + // Room or Apt
                " status VARCHAR(20), " + // status - InProgress, Resolved
                " description VARCHAR(1000), " +
                " approved_by " + ColumnTypes.ID_TYPE + " ," +
                " approved_on timestamp, " +
                " PRIMARY KEY (ticket_no), " +
                " FOREIGN KEY (ticket_type) REFERENCES TICKET_SEVERITY  , " +
                " FOREIGN KEY (res_id) REFERENCES RESIDENT(res_id), " +
                " FOREIGN KEY (approved_by) REFERENCES STAFF " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Water', '06-Apr-2015', '100540001', 'HID1_O1', 'Room', 'Pending', 'No Water', null, null)"; // HID1_O1 from Room table.
        String query2 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Internet', '30-Mar-2015', '100540003', 'HID1_O1', 'Room', 'Complete', 'Internet Not Working', '300220001', '1-Apr-2015')"; // HID1_O1 from Room table.
        String query3 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Cleaning', '15-Mar-2015', '100540007', 'HID1_O1', 'Room', 'Complete', 'Cleaning', '300220002', '5-Mar-2015')"; // HID1_O1 from Room table.
        String query4 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Miscellaneous', '02-Apr-2015', '200540002', 'HID1_O1', 'Room', 'Pending', 'Window broken', null, null)"; // HID1_O1 from Room table.

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        DBAccessor.executeBatchQuery(conn, queries);
    }

    public String insertRequest(Connection conn, String resident_id, TicketRequest ticketRequest) {

        String query1 = "SELECT count(*) from LEASE_REQUEST where RES_ID = '" + resident_id + "' AND STATUS = 'InProgress'";

        ResultSet resultSet = null;
        try {
            resultSet = DBAccessor.selectQuery(conn, query1);
            while (resultSet.next()) {
                if (resultSet.getInt(1) <= 0) {
                    return "Cannot Request Maintenance Ticket. No valid lease in progress";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO "
                + getTableName()
                + " VALUES(ticket_sequence.NEXTVAL, "
                + "'" + ticketRequest.getTicket() + "'" + ", "
                + "CURRENT_TIMESTAMP" + ", "
                + "'" + resident_id + "'" + ", "
                + "'" + "F2" + "'" + ", "
                + "'" + "Apt" + "'" + ", " +
                "'Pending', "
                + "'" + ticketRequest.getDescription() + "'" + ", "
                + "null, null"
                + ")";

        System.out.println(query);

        try {
            executeQuery(conn, query);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        getTickets(conn, resident_id);
        return "SUCCESS";
    }

        public ArrayList<TicketRequest> getTicketsToResolve(Connection conn, String staffId) {
            ArrayList<TicketRequest> tickets = new ArrayList<>();

            String hallName = "";
            String query = "SELECT work_location from STAFF where staff_num = " + "'" + staffId + "'";

            try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
                while(resultSet.next()){
                    hallName = resultSet.getString("work_location");
                }
            }catch (SQLException ex){
                System.err.println("Error Occurred During get hall name of staff " + ex.getMessage());
            }

            query = "SELECT R.FNAME as FNAME, R.LNAME as LNAME, M.res_id as res_id, " +
                    " M.ticket_type as ticket_type, M.ticket_date as ticket_date, " +
                    "M.status as status, M.ticket_no as ticket_no, M.description as description, " +
                    " M.approved_by as approved_by, M.approved_on as approved_on, " +
                    "TS.SEVERITY as severity " +
                    " FROM "
                    + getTableName() + " M, "
                    + new ResidentTable().getTableName() + " R,"
                    + new TicketSeverityTable().getTableName() + " TS"
                    + " WHERE "
                    + "M.RES_ID = R.RES_ID"
                    + " AND M.STATUS <> 'Complete'"
                    + " AND TS.TICKET_TYPE = M.TICKET_TYPE"
                    + " ORDER BY CASE TS.severity"
                    + "  WHEN 'Low' THEN 3"
                    + "  WHEN 'Medium' THEN 2"
                    + "  WHEN 'High' THEN 1"
                    + "  END, M.ticket_date";

            try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
                while(resultSet.next()){
                    TicketRequest ticketRequest = new TicketRequest();

                    ticketRequest.setResidentId(resultSet.getString("res_id"));
                    String address = getAddress(conn, ticketRequest.getResidentId());
                    
                    ticketRequest.setTicket(resultSet.getString("ticket_type"));
                    ticketRequest.setStatus(resultSet.getString("status"));
                    ticketRequest.setDescription(resultSet.getString("description"));
                    ticketRequest.setDate(resultSet.getTimestamp("ticket_date"));
                    ticketRequest.setResidentId(resultSet.getString("FNAME") + " " + resultSet.getString("LNAME"));
                    ticketRequest.setTicket_no(resultSet.getInt("ticket_no"));
                    ticketRequest.setSeverity(resultSet.getString("severity"));
                    ticketRequest.setAddress(address);
                    if (ticketRequest.getAddress().contains(hallName)) {
                        tickets.add(ticketRequest);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error Occurred During get tickets for resolve query " + ex.getMessage());
            }

            return tickets;
        }

    private String getAddress(Connection conn, String residentId) {
        String address = "";

        String query1 = "SELECT * from LEASE_REQUEST where RES_ID = '" + residentId + "' AND STATUS = 'InProgress'";
        Integer requestNumber = 0;
        ResultSet resultSet = null;
        try {
            resultSet = DBAccessor.selectQuery(conn, query1);
            while (resultSet.next()) {
                requestNumber = resultSet.getInt("request_number");
                System.out.println("Request number = " + requestNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query1 = "SELECT * from LEASE where request_number = '" + Integer.toString(requestNumber) + "'";
        resultSet = null;
        String HousingId = "", LocationNo = "";
        try {
            resultSet = DBAccessor.selectQuery(conn, query1);
            while (resultSet.next()) {
                HousingId = resultSet.getString("housing_id");
                LocationNo = resultSet.getString("location_no");
                System.out.println(HousingId);
                System.out.println(LocationNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        query1 = "SELECT * from HOUSING where housing_id = '" + HousingId + "'";
        resultSet = null;
        String HallAptName="", type="", RoomAptNo="";
        try {
            resultSet = DBAccessor.selectQuery(conn, query1);
            while (resultSet.next()) {
                HallAptName = resultSet.getString("name");
                type = resultSet.getString("type");
                System.out.println(HousingId);
                System.out.println(LocationNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        address = HallAptName + "-" + LocationNo;
        return address;
    }

    public ArrayList<TicketRequest> getTickets(Connection conn, String resident_id) {
        ArrayList<TicketRequest> tickets = new ArrayList<>();

        String query = "SELECT * FROM "
                + getTableName()
                + " WHERE "
                + "RES_ID = '"
                + resident_id
                + "'";

        System.out.println(query);

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                TicketRequest ticketRequest = new TicketRequest();
                StaffNameView staffNameView = new StaffNameView();

                ticketRequest.setTicket(resultSet.getString("ticket_type"));
                ticketRequest.setStatus(resultSet.getString("status"));
                ticketRequest.setDescription(resultSet.getString("description"));
                ticketRequest.setDate(resultSet.getTimestamp("ticket_date"));

                ticketRequest.setApproved_on(resultSet.getTimestamp("approved_on"));
                ticketRequest.setApproved_by(staffNameView.getStaffName(conn, resultSet.getString("approved_by")));

                tickets.add(ticketRequest);
            }


        }catch (SQLException ex){
            System.err.println("Error Occurred During get tickets query " + ex.getMessage());
        }

        return tickets;
    }

    public ArrayList<TicketRequest> resolve(Connection conn, String ticket_no, Map<String, Object> sessionMap, String current_ticket_status) {
        if (current_ticket_status.equals("Pending")) {
            System.out.println("Pending");
            String query = "UPDATE " + getTableName()
                    + " SET STATUS = 'Processing', " +
                    "approved_on = CURRENT_TIMESTAMP, " +
                    "approved_by = " + "'" + sessionMap.get("username") + "'"
                    + " WHERE TICKET_NO = " + ticket_no;
            System.out.println(query);

            try {
                DBAccessor.executeQuery(conn, query);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Processing");
            String query = "UPDATE " + getTableName()
                    + " SET STATUS = 'Complete', " +
                    "approved_on = CURRENT_TIMESTAMP, " +
                    "approved_by = " + "'" + sessionMap.get("username") + "'"
                    + " WHERE TICKET_NO = " + ticket_no;
            System.out.println(query);

            try {
                DBAccessor.executeQuery(conn, query);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return getTicketsToResolve(conn, (String) sessionMap.get("username"));
    }
}
